package sevencode.util.dynamic.datasource.aspect;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:00
 * @Description: 切换数据源路由
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
public class DSRoutingByUser extends AbstractRoutingDataSource {
    /******************************************************
     * @Description : 根据当前线程内绑定的数据源进行动态切换
     * @Return : 当前线程内绑定的数据源
     * @Date : 2021/3/26 11:04
     *******************************************************/
    @Override
    protected Object determineCurrentLookupKey() {
        return DSContext.getDS();
    }
}
