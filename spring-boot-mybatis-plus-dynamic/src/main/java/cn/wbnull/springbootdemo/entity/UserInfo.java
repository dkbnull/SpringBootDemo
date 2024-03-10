package cn.wbnull.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * UserInfo
 *
 * @author dukunbiao(null)  2024-03-04
 * https://github.com/dkbnull/SpringBootDemo
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userCode")
    private String userCode;

    @TableField("userName")
    private String userName;

    private String password;
}
