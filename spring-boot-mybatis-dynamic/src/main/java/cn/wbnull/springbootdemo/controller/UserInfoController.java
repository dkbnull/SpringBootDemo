package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserInfoController
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

    @PostMapping(value = "query")
    public List<User> query() {
        return userInfoService.query();
    }
}
