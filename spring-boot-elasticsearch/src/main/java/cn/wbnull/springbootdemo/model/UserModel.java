package cn.wbnull.springbootdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * UserModel
 *
 * @author dukunbiao(null)  2024-04-07
 * https://github.com/dkbnull/SpringBootDemo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserModel {

    private String username;
    private String password;
}
