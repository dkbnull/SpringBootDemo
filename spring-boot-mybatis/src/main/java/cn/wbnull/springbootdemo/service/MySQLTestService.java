package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.dao.TestMapper;
import cn.wbnull.springbootdemo.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mysql test 服务类
 *
 * @author dukunbiao(null)  2019-02-14
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class MySQLTestService {

    @Autowired
    private TestMapper testMapper;  //这里会有报错，不用管

    public List<TestModel> select() {
        return testMapper.select();
    }

    public int insert(String name) {
        return testMapper.insert(name);
    }
}
