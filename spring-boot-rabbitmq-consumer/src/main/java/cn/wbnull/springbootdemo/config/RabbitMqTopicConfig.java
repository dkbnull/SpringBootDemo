package cn.wbnull.springbootdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMqTopicConfig
 *
 * @author dukunbiao(null)  2024-04-08
 * https://github.com/dkbnull/SpringBootDemo
 */
@Configuration
public class RabbitMqTopicConfig {

    /**
     * Topic 交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic_user_exchange", true, false);
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
        return new Queue("sms.topic.queue", true);
    }

    /**
     * 发送邮件队列
     *
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email.topic.queue", true);
    }

    /**
     * 发送微信队列
     *
     * @return
     */
    @Bean
    public Queue wechatQueue() {
        return new Queue("wechat.topic.queue", true);
    }

    /**
     * durable         创建持久化队列phone.topic.queue
     * withArgument    设置消息过期时间60000毫秒
     *
     * @return
     */
    @Bean
    public Queue phoneQueue() {
        return QueueBuilder.durable("phone.topic.queue").withArgument("x-message-ttl", 60000).build();
    }

    @Bean
    public Queue dlxQueue() {
        return new Queue("dlx.topic.queue", true);
    }

    @Bean
    public Queue vipQueue() {
        return QueueBuilder.durable("vip.topic.queue")
                .withArgument("x-message-ttl", 60000)
                .withArgument("x-dead-letter-exchange", "dlx.topic.queue")
                .build();
    }

    /**
     * 将队列和交换机绑定, 并设置用于匹配键
     *
     * @return
     */
    @Bean
    public Binding smsBindingTopic() {
        return BindingBuilder.bind(smsQueue()).to(topicExchange()).with("*.sms.#");
    }

    @Bean
    public Binding emailBindingTopic() {
        return BindingBuilder.bind(emailQueue()).to(topicExchange()).with("#.email.#");
    }

    @Bean
    public Binding wechatBindingTopic() {
        return BindingBuilder.bind(wechatQueue()).to(topicExchange()).with("#.wechat.*");
    }

    @Bean
    public Binding phoneBindingTopic() {
        return BindingBuilder.bind(phoneQueue()).to(topicExchange()).with("#.phone.#");
    }

    @Bean
    public Binding dlxBindingTopic() {
        return BindingBuilder.bind(dlxQueue()).to(topicExchange()).with("#.#");
    }

    @Bean
    public Binding vipBindingTopic() {
        return BindingBuilder.bind(vipQueue()).to(topicExchange()).with("#.vip.#");
    }
}
