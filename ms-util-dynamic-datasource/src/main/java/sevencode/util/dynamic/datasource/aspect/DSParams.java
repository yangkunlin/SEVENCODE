package sevencode.util.dynamic.datasource.aspect;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 11:18
 * @Description: 数据源相关静态参数
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
public interface DSParams {
    String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    String DRIVER_CJ_CLASS = "com.mysql.cj.jdbc.Driver";
    String IS_PRIVATE_DB = "1";
}
