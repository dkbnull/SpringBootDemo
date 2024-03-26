package cn.wbnull.springbootdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserModel
 *
 * @author dukunbiao(null)  2024-03-16
 * https://github.com/dkbnull/SpringBootDemo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

    private int id;
    private String name;
}
