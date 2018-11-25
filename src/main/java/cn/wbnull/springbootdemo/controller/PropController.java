package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.config.ConfigProp;
import cn.wbnull.springbootdemo.config.ConfigValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取properties配置文件
 *
 * @author dukunbiao(null)  2018-09-23
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
public class PropController {

    @Autowired
    private ConfigValue configValue;

    @Autowired
    private Environment environment;

    @Autowired
    private ConfigProp configProp;

    @RequestMapping(value = "/properties")
    public String properties() {
        return "get properties value by ''@Value'' :" +
                //1、使用@Value注解读取
                " name=" + configValue.name +
                " , age=" + configValue.age +
                "<p>get properties value by ''Environment'' :" +
                //2、使用Environment读取
                " sex=" + environment.getProperty("demo.sex") +
                " , address=" + environment.getProperty("demo.address") +
                "<p>get properties value by ''@ConfigurationProperties'' :" +
                //3、使用@ConfigurationProperties注解读取
                " phone=" + configProp.getPhone() +
                " , wife=" + configProp.getWife();
    }
}
