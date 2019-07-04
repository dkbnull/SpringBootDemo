package cn.wbnull.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 *
 * @author dukunbiao(null)  2018-08-18
 * https://github.com/dkbnull/SpringBootDemo
 */
@SpringBootApplication
@MapperScan("cn.wbnull.springbootdemo.dao")
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
