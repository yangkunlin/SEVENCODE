package sevencode.util.dynamic.datasource.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sevencode.util.dynamic.datasource.config.ConfigMaster;
import sevencode.util.dynamic.datasource.util.DSPoolUtil;
import sevencode.util.dynamic.datasource.util.DSPoolUtilDefault;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:50
 * @Description: 数据源切换切面类
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Component
@Aspect
@Configuration
public class DSAspect implements Ordered {
    private static DataSource dataSource = null;
    //数据源线程控制组件
    @Autowired
    private DSContext dsContext;
    //主数据源配置对象
    @Autowired
    private ConfigMaster configMaster;
    //数据源动态选择器
    @Autowired
    @Qualifier(value = "defaultDS")
    private RoutingDSByUser routingDSByUser;

    /******************************************************
     * @Description : 指定以注解DSRoutingPrivate为切面
     * @Return :
     * @Date : 2021/3/26 14:20
     *******************************************************/
    @Pointcut("@annotation(sevencode.util.dynamic.datasource.annotation.DSRoutingPrivate)")
    public void dataSourcePointcut() {
    }

    /******************************************************
     * @Description : 指定业务处理前进行拦截并切换数据源
     * @Return :
     * @Date : 2021/3/26 14:22
     *******************************************************/
    @Before(value = "dataSourcePointcut()")
    public void before(JoinPoint joinPoint) throws SQLException {
        /*********** 这里的租户信息应该是从基础数据库中获取 *************/
        //实际项目中用户id应从token中获取
        String userId = "1";
        //从基础库中获取租户信息
        DataSource dataSourceDefault = DSPoolUtilDefault.getPoolProperties(configMaster.getUrl(), configMaster.getUsername(), configMaster.getPassword());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceDefault);
        List<Map<String, Object>> tenementInfoList = jdbcTemplate.queryForList("SELECT * FROM tenement_info t WHERE t.id = " + userId);
        //关闭基础库连接
        dataSourceDefault.getConnection().close();
        Map<String, Object> map = tenementInfoList.get(0);
        /********************************************************/
        if (map.containsKey("is_private_db") && map.get("is_private_db").equals(DSParams.IS_PRIVATE_DB)) {
            dataSource = DSPoolUtil.getPoolProperties(
                    map.getOrDefault("private_db_url", configMaster.getUrl()).toString(),
                    map.getOrDefault("private_db_user", configMaster.getUsername()).toString(),
                    map.getOrDefault("private_db_password", configMaster.getPassword()).toString());
            //使用hashMap缓存数据源链接
            Map<Object, Object> dsMap = new HashMap<>();
            dsMap.put("private", dataSource);
            //放入路由选择器中
            routingDSByUser.setTargetDataSources(dsMap);
            //动态切换数据源 并手动注入私有库数据源
            routingDSByUser.afterPropertiesSet();
            dsContext.setDS("private");
        } else {
            dsContext.setDS("master");
        }
    }

    /******************************************************
     * @Description : 切面后置处理，回收链接
     * @Return :
     * @Date : 2021/4/6 20:26
     *******************************************************/
    @After(value = "dataSourcePointcut()")
    public void after(JoinPoint joinPoint) throws SQLException {
        if (!dataSource.getConnection().isClosed()) {
            dataSource.getConnection().close();
        }
        //清除缓存
        dsContext.removeDS();
    }

    /******************************************************
     * @Description : 根据返回值设置切面执行顺序优先级
     * @Return : 返回值越小，优先级越高
     * @Date : 2021/3/26 11:52
     *******************************************************/
    @Override
    public int getOrder() {
        return 0;
    }
}
