package cn.wbnull.springbootdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * FanoutWechatService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@RabbitListener(queues = "wechat.fanout.queue")
@Service
public class FanoutWechatService {

    /**
     * 消息接收的方法
     *
     * @param message
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        //推送微信消息

        System.out.println("wechat.fanout：" + message);
    }
}
