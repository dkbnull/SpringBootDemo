package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.mapper.master.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(String name) {
        User user = new User();
        user.setName(name);

        userMapper.add(user);

        return "操作成功";
    }

    public List<User> query() {
        return userMapper.query();
    }

    public String update(int id, String name) {
        userMapper.update(id, name);

        return "操作成功";
    }

    public String delete(int id) {
        userMapper.delete(id);

        return "操作成功";
    }
}
