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
@Configuration
@Data
public class ConfigMaster {
    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String url;
    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String username;
    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;
    @Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
    private String driverClassName;

}
