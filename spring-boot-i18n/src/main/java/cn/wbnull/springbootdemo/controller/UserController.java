package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author dukunbiao(null)  2024-03-28
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public String login() {
        return userService.login();
    }

    @PostMapping(value = "loginSuccess")
    public String loginSuccess(@RequestParam("username") String username) {
        return userService.loginSuccess(username);
    }
}
