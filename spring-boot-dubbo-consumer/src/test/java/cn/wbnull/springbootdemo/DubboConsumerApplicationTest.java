package cn.wbnull.springbootdemo;

import cn.wbnull.springbootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboConsumerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DubboConsumerApplicationTest {

    @Autowired
    public UserService userService;

    @Test
    public void contextLoads() {
        String userInfo = userService.getUserInfo();
        System.out.println("用户信息：" + userInfo);
    }
}
