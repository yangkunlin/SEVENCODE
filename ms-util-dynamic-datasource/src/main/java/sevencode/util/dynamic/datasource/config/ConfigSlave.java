package sevencode.util.dynamic.datasource.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/***********************************************
 * @Author: Seven
 * @Date: 2021/3/26 9:36 上午
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@Configuration
@Data
public class ConfigSlave {
    @Value("${spring.datasource.dynamic.datasource.slave.url}")
    private String url;
    @Value("${spring.datasource.dynamic.datasource.slave.username}")
    private String username;
    @Value("${spring.datasource.dynamic.datasource.slave.password}")
    private String password;
    @Value("${spring.datasource.dynamic.datasource.slave.driver-class-name}")
    private String driverClassName;
}
