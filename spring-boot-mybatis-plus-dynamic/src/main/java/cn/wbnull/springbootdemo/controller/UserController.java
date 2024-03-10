package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author dukunbiao(null)  2024-03-04
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping(value = "query")
    public List<User> query() {
        return userService.query();
    }
}
