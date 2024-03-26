package cn.wbnull.springbootdemo;

import cn.wbnull.springbootdemo.model.UserModel;
import cn.wbnull.springbootdemo.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest(classes = RedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RedisApplicationTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("test_key", "test_value");
        System.out.println(redisTemplate.opsForValue().get("test_key"));
    }

    @Test
    public void contextLoadsUser() {
        UserModel user = new UserModel(1, "测试姓名");
        redisTemplate.opsForValue().set("test_user", user);
        System.out.println(redisTemplate.opsForValue().get("test_user"));
    }

    @Test
    public void contextLoadsUtil() {
        redisUtils.set("test_utils_key", "test_utils_value");
        System.out.println(redisUtils.get("test_utils_key"));
    }
}
