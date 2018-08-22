package cn.wbnull.springbootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取 properties 配置文件
 *
 * @author dukunbiao(null)  2018-08-22
 */
@Component
public class ConfigBeanValue {

    @Value("${demo.name}")
    public String name;

    @Value("${demo.age}")
    public String age;
}
