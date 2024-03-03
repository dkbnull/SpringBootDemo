package cn.wbnull.springbootdemo.entity;

import lombok.Data;

/**
 * UserInfo
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@Data
public class UserInfo {

    private Integer id;
    private String userCode;
    private String userName;
    private String password;
}
