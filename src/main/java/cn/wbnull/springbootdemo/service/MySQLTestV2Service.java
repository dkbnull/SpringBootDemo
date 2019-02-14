package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.dao.TestV2Mapper;
import cn.wbnull.springbootdemo.model.TestV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Mysql testv2 服务类
 *
 * @author dukunbiao(null)  2019-02-14
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class MySQLTestV2Service {

    @Autowired
    private TestV2Mapper testV2Mapper;  //这里会有报错，不用管

    public int insert(int id, String username, String password) {
        TestV2 testV2 = new TestV2();
        testV2.setId(id);
        testV2.setUsername(username);
        testV2.setPassword(password);

        return testV2Mapper.insert(testV2);
    }
}
