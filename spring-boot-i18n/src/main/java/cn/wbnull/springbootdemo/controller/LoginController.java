package cn.wbnull.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController
 *
 * @author dukunbiao(null)  2024-03-28
 * https://github.com/dkbnull/SpringBootDemo
 */
@Controller
public class LoginController {

    @GetMapping(value = "login.html")
    public String login() {
        return "login";
    }
}
