package cn.wbnull.springbootdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author dukunbiao(null)  2024-04-28
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@RequestMapping("user")
//@CrossOrigin(origins = "*")
public class UserController {

    @PostMapping(value = "login")
    public String login() {
        return "姓名：张三；性别：男";
    }

//    @PostMapping(value = "login")
//    public String login(HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//
//        return "姓名：张三；性别：男";
//    }
}
