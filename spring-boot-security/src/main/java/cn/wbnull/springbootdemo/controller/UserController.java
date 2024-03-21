package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.model.UserModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 *
 * @author dukunbiao(null)  2024-03-12
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping(value = "query")
    @Secured("ROLE_level1")
    public String query() {
        return "用户查询成功";
    }

    @GetMapping(value = "update")
    @Secured({"ROLE_level1", "ROLE_level2"})
    public String update() {
        return "用户更新成功";
    }

    @GetMapping(value = "delete")
    @PreAuthorize("hasAnyAuthority('ROLE_level1','level3')")
    //@PostAuthorize("hasAnyAuthority('ROLE_level1','level3')")
    public String delete() {
        return "用户删除成功";
    }

    @GetMapping(value = "queryList")
    @PreAuthorize("hasAnyAuthority('ROLE_level1','level3')")
    @PostFilter("filterObject.username == 'test'")
    public List<UserModel> queryList() {
        List<UserModel> userList = new ArrayList<>();
        userList.add(new UserModel("test", "qwerty"));
        userList.add(new UserModel("test2", "asdfgh"));
        userList.add(new UserModel("test3", "zxcvbn"));

        return userList;
    }

    @PostMapping(value = "queryUser")
    @PreAuthorize("hasAnyAuthority('ROLE_level1','level3')")
    @PreFilter("filterObject.username == 'test2'")
    public List<UserModel> queryUser(@RequestBody List<UserModel> userModels) {
        return userModels;
    }
}
