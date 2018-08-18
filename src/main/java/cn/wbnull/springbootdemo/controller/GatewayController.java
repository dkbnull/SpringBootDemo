package cn.wbnull.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2018-08-18
 */
@RestController
public class GatewayController {

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "Hello World";
    }
}
