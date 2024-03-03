package cn.wbnull.springbootdemo.mapper.master;

import cn.wbnull.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author dukunbiao(null)  2024-03-03
 * https://github.com/dkbnull/SpringBootDemo
 */
@Repository
public interface UserMapper {

    void add(@Param("user") User user);

    List<User> query();

    void update(@Param("id") int id, @Param("name") String name);

    void delete(@Param("id") int id);
}
