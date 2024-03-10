package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-03-04
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> query() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        return userMapper.selectList(queryWrapper);
    }
}
