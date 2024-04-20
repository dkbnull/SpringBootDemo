package cn.wbnull.springbootdemo.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * TopicDlxService
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@RabbitListener(queues = "dlx.topic.queue")
@Service
public class TopicDlxService {

    /**
     * 消息接收的方法
     *
     * @param message
     */
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("dlx.topic：" + message);
    }
}
