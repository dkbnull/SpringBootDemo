package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.entity.UserInfo;
import cn.wbnull.springbootdemo.mapper.UserInfoMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserInfoService
 *
 * @author dukunbiao(null)  2024-03-04
 * https://github.com/dkbnull/SpringBootDemo
 */
@DS("slave")
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> query() {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();

        return userInfoMapper.selectList(queryWrapper);
    }
}
