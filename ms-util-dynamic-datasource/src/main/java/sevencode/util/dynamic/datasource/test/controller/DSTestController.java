package sevencode.util.dynamic.datasource.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sevencode.util.dynamic.datasource.annotation.DSRoutingPrivate;
import sevencode.util.dynamic.datasource.test.service.DSTestService;

/***********************************************
 * @Author: Seven
 * @Date: 2021/4/7 16:05
 * @Description:
 * @Modified:
 * @Version: 1.0.0
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/dynamic/datasource/v1")
public class DSTestController {

    @Autowired
    private DSTestService dsTestService;

    @GetMapping("/test")
    @DSRoutingPrivate
    public String test() {
        return dsTestService.test();
    }

}
