package cn.wbnull.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 *
 * @author dukunbiao(null)  2024-02-19
 * https://github.com/dkbnull/SpringBootDemo
 */
@SpringBootApplication
@MapperScan("cn.wbnull.springbootdemo.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
