package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.callback.MessageConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void register(String username, String password) {
        //模拟用户注册
        String userId = UUID.randomUUID().toString();

        //saveUser(user);

        //发送用户信息给RabbitMQ fanout
        rabbitTemplate.convertAndSend("fanout_user_exchange", "", userId + username);
    }

    public void registerDirect(String username, String password) {
        //模拟用户注册
        String userId = UUID.randomUUID().toString();

        //saveUser(user);

        rabbitTemplate.convertAndSend("direct_user_exchange", "sms", userId + username);
        rabbitTemplate.convertAndSend("direct_user_exchange", "email", userId + username);
    }

    public void registerTopic(String username, String password) {
        //模拟用户注册
        String userId = UUID.randomUUID().toString();

        //saveUser(user);

        rabbitTemplate.convertAndSend("topic_user_exchange", "*.sms.email.*", userId + username);
    }

    public void registerTopicCallback(String username, String password) {
        //模拟用户注册
        String userId = UUID.randomUUID().toString();

        //saveUser(user);

        //设置消息确认
        rabbitTemplate.setConfirmCallback(new MessageConfirmCallback());
        rabbitTemplate.convertAndSend("topic_user_exchange", "*.sms.email.*", userId + username);
    }

    public void registerTopicDlx(String username, String password) {
        //模拟用户注册
        String userId = UUID.randomUUID().toString();

        //saveUser(user);

        rabbitTemplate.convertAndSend("topic_user_exchange", "*.vip.*", userId + username);
    }
}
