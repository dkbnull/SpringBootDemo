package cn.wbnull.springbootdemo.mapper.slave;

import cn.wbnull.springbootdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInfoMapper
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@Repository
public interface UserInfoMapper {

    List<User> query();
}
