package sevencode.util.dynamic.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************************
 * @Author: Seven
 * @Date: 2021/4/7 16:32
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DSRoutingPrivate {
}
