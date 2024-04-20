package cn.wbnull.springbootdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * TopicSmsService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@RabbitListener(queues = "sms.topic.queue")
@Service
public class TopicSmsService {

    /**
     * 消息接收的方法
     *
     * @param message
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        //发送短信

        System.out.println("sms.topic：" + message);
    }
}
