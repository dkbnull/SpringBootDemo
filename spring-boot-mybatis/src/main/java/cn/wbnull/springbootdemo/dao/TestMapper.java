package cn.wbnull.springbootdemo.dao;

import cn.wbnull.springbootdemo.model.TestModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * test dao
 *
 * @author dukunbiao(null)  2019-02-14
 * https://github.com/dkbnull/SpringBootDemo
 */
@Mapper
@Repository
public interface TestMapper {

    @Select("select id,name from test")
    List<TestModel> select();

    @Insert("insert into test(name) values(#{name})")
    int insert(@Param("name") String name);
}
