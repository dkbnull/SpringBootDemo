package cn.wbnull.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 入口类
 *
 * @author dukunbiao(null)  2018-08-18
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@ApiIgnore
public class GatewayController extends BaseController {

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "Hello World";
    }
}
