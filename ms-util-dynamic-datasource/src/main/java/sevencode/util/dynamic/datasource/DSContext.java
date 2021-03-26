package sevencode.util.dynamic.datasource;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 10:47
 * @Description: 主从数据源切换时线程安全控制
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
public class DSContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /******************************************************
     * @Description : dsName-数据源参数名称
     * @Return :
     * @Date : 2021/3/26 10:57
     *******************************************************/
    public static void setDS(String dsName) {
        contextHolder.set(dsName);
    }

    /******************************************************
     * @Description :
     * @Return :
     * @Date : 2021/3/26 14:16
     *******************************************************/
    public static String getDS() {
        return contextHolder.get();
    }

    /******************************************************
     * @Description :
     * @Return :
     * @Date : 2021/3/26 14:18
     *******************************************************/
    public static void removeDS() {
        contextHolder.remove();
    }

}
