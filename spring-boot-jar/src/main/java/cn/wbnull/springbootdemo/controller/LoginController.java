package cn.wbnull.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController
 *
 * @author dukunbiao(null)  2024-04-06
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
public class LoginController {

    @GetMapping(value = "login")
    public String login() {
        JSONObject params = new JSONObject();
        params.put("username", "test");

        return params.toString();
    }
}
