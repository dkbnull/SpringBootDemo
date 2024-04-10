package cn.wbnull.springbootdemo.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-03-25
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    /**
     * 使用服务提供者提供的服务，需要先从注册中心中获取服务
     *
     * @DubboReference 引用，使用方式：1、pom坐标引用   2、定义路径相同的接口名
     */
    @DubboReference
    public UserInfoService userInfoService;

    public String getUserInfo() {
        return userInfoService.getUserInfo();
    }
}
