package cn.wbnull.springbootdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 通过@ConfigurationProperties 读取properties配置文件
 *
 * @author dukunbiao(null)  2018-08-22
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Component
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "demo.properties")
public class PropConfigV2 {

    private String phone;
    private String wife;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }
}
