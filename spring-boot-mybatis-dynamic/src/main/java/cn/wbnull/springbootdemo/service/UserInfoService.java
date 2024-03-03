package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.entity.User;
import cn.wbnull.springbootdemo.mapper.slave.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserInfoService
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<User> query() {
        return userInfoMapper.query();
    }
}
