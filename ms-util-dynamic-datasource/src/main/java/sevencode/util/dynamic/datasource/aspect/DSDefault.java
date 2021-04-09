package sevencode.util.dynamic.datasource.aspect;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import sevencode.util.dynamic.datasource.config.ConfigMaster;
import sevencode.util.dynamic.datasource.util.DSPoolUtilDefault;

import java.util.HashMap;
import java.util.Map;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:25
 * @Description: 默认数据源自动加载类
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Configuration
@Component
public class DSDefault {
    private final String driverClass = DSParams.DRIVER_CJ_CLASS;
    @Autowired
    private ConfigMaster configMaster;

    /******************************************************
     * @Description : 默认数据源----->从连接池获得
     * ---->为该数据源起名为 master---->交由Spring容器管理
     * @Return :
     * @Date : 2021/3/26 11:30
     *******************************************************/
    @Bean(name = "master")
    public DataSource masterDataSource() {
        DataSource dataSource = DSPoolUtilDefault.getPoolProperties(
                configMaster.getUrl(),
                configMaster.getUsername(),
                configMaster.getPassword());
        return dataSource;
    }

    /******************************************************
     * @Description : 自动装配时当出现多个Bean候选者时
     * 被注解为@Primary的Bean将作为首选者 否则将抛出异常
     * @Return :
     * @Date : 2021/3/26 11:30
     *******************************************************/
    @Primary
    @Bean(name = "defaultDS")
    public RoutingDSByUser RouteDataSource() throws Exception {
        RoutingDSByUser defaultDS = new RoutingDSByUser();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource());
        //将map中我们创建的默认数据源赋值
        defaultDS.setTargetDataSources(targetDataSources);
        //以该默认数据源为默认访问数据库的链接使用
        defaultDS.setDefaultTargetDataSource(masterDataSource());
        return defaultDS;
    }
}
