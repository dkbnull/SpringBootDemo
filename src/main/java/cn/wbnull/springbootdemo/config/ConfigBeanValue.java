package cn.wbnull.springbootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过@Value 读取properties配置文件
 *
 * @author dukunbiao(null)  2018-08-22
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Component
public class ConfigBeanValue {

    @Value("${demo.name}")
    public String name;

    @Value("${demo.age}")
    public String age;
}
