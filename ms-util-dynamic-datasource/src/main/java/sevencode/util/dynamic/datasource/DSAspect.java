package sevencode.util.dynamic.datasource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import sevencode.util.dynamic.datasource.config.ConfigMaster;
import sevencode.util.dynamic.datasource.test.entity.TenementInfo;

import javax.sql.DataSource;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:50
 * @Description:
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
    @Autowired
    private DSContext dsContext;
    private ConfigMaster configMaster;
    @Autowired
    @Qualifier(value = "dynamicDS")
    private RoutingDSByUser routingDSByUser;

    /******************************************************
     * @Description : 指定以controller为切面
     * @Return :
     * @Date : 2021/3/26 14:20
     *******************************************************/
    @Pointcut("execution(* sevencode.util.dynamic.datasource.test.controller..*(..))")
    public void dataSourcePointcut() {
    }

    /******************************************************
     * @Description : 指定业务处理前进行拦截并切换数据源
     * @Return :
     * @Date : 2021/3/26 14:22
     *******************************************************/
    @Before(value = "dataSourcePointcut()")
    public void before(JoinPoint joinPoint) {
        /*********** 这里的租户信息应该是从基础数据库中获取 *************/
        TenementInfo tenementInfo = new TenementInfo();
        tenementInfo.setTenementId("1");
        tenementInfo.setUserId("1");
        tenementInfo.setIsPrivateDb("1");
        tenementInfo.setPrivateDbUrl("");
        tenementInfo.setPrivateDbType("");
        tenementInfo.setPrivateDbUser("");
        tenementInfo.setPrivateDbPassword("");
        /********************************************************/
        if (StringUtils.isNoneEmpty(tenementInfo.getIsPrivateDb()) && tenementInfo.getIsPrivateDb().equals("1")) {

            dataSource = DSPoolUtil.getPoolProperties(
                    StringUtils.isNotEmpty(tenementInfo.getPrivateDbUrl()) ? tenementInfo.getPrivateDbUrl() : configMaster.getUrl(),
                    StringUtils.isNotEmpty(tenementInfo.getPrivateDbUser()) ? tenementInfo.getPrivateDbUser(): configMaster.getUsername(),
                    StringUtils.isNotEmpty(tenementInfo.getPrivateDbPassword()) ? tenementInfo.getPrivateDbPassword(): configMaster.getPassword());
        }
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
