package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2019-07-10
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
public class GatewayController {

    @RequestMapping(value = "/gateway")
    public String gateway() {
        LoggerUtils.info("Hello World");
        LoggerUtils.info("127.0.0.1", "前台请求信息", "/gateway", "Hello World");
        return "Hello World";
    }
}
