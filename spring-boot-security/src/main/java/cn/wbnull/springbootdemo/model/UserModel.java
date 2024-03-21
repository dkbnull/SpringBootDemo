package cn.wbnull.springbootdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserModel
 *
 * @author dukunbiao(null)  2024-03-12
 * https://github.com/dkbnull/SpringBootDemo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String username;
    private String password;
}
