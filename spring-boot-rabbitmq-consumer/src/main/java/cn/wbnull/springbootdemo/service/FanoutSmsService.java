package cn.wbnull.springbootdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * FanoutSmsService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@RabbitListener(queues = "sms.fanout.queue")
@Service
public class FanoutSmsService {

    /**
     * 消息接收的方法
     *
     * @param message
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        //发送短信

        System.out.println("sms.fanout：" + message);
    }
}
