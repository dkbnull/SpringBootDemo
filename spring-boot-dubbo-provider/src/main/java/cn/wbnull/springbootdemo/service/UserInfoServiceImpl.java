package cn.wbnull.springbootdemo.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * UserInfoServiceImpl
 *
 * @author dukunbiao(null)  2024-03-25
 * https://github.com/dkbnull/SpringBootDemo
 */
@DubboService
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public String getUserInfo() {
        return "name:zhangsan;age:18";
    }
}
