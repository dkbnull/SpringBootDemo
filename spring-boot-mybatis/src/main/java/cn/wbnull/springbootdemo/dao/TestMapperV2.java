package cn.wbnull.springbootdemo.dao;

import cn.wbnull.springbootdemo.model.TestModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * test dao
 *
 * @author dukunbiao(null)  2019-02-14
 * https://github.com/dkbnull/SpringBootDemo
 */
@Repository
public interface TestMapperV2 {

    List<TestModel> select();

    int insert(TestModel testModel);
}
