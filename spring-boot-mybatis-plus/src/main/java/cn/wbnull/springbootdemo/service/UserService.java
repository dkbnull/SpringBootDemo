package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-02-19
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(String name) {
        User user = new User();
        user.setName(name);

        userMapper.insert(user);

        return "操作成功";
    }

    public List<User> query() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        return userMapper.selectList(queryWrapper);
    }

    public String update(int id, String name) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getName, name);
        updateWrapper.eq(User::getId, id);

        userMapper.update(updateWrapper);

        return "操作成功";
    }

    public String delete(int id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);

        userMapper.delete(queryWrapper);

        return "操作成功";
    }
}
