package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.dao.TestMapperV2;
import cn.wbnull.springbootdemo.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mysql test 服务类
 *
 * @author dukunbiao(null)  2019-02-14
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class MySQLTestServiceV2 {

    @Autowired
    private TestMapperV2 testMapperV2;  //这里会有报错，不用管

    public List<TestModel> select() {
        return testMapperV2.select();
    }

    public int insert(int id, String name) {
        TestModel testModel = new TestModel();
        testModel.setId(id);
        testModel.setName(name);

        return testMapperV2.insert(testModel);
    }
}
