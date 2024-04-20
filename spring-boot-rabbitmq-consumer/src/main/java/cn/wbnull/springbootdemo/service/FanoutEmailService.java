package cn.wbnull.springbootdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * FanoutEmailService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@RabbitListener(queues = "email.fanout.queue")
@Service
public class FanoutEmailService {

    /**
     * 消息接收的方法
     *
     * @param message
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        //发送邮件

        System.out.println("email.fanout：" + message);
    }
}
