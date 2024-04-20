package cn.wbnull.springbootdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMqFanoutConfig
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@Configuration
public class RabbitMqFanoutConfig {

    /**
     * Fanout 交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_user_exchange", true, false);
    }

    /**
     * 发送短信队列
     *
     * @return
     */
    @Bean
    public Queue smsQueue() {
        //durable       是否持久化，默认为false
        //exclusive     是否只能被当前创建的连接使用，默认为false
        //autoDelete    是否自动删除，默认为false

        //一般设置队列的持久化，其余两个默认false
        return new Queue("sms.fanout.queue", true);
    }

    /**
     * 发送邮件队列
     *
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue", true);
    }

    /**
     * 发送微信队列
     *
     * @return
     */
    @Bean
    public Queue wechatQueue() {
        return new Queue("wechat.fanout.queue", true);
    }

    /**
     * 将队列和交换机绑定
     *
     * @return
     */
    @Bean
    public Binding smsBindingFanout() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBindingFanout() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding wechatBindingFanout() {
        return BindingBuilder.bind(wechatQueue()).to(fanoutExchange());
    }
}
