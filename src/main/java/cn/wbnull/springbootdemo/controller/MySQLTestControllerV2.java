package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.model.TestModel;
import cn.wbnull.springbootdemo.service.MySQLTestServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Mysql test 控制类
 *
 * @author dukunbiao(null)  2019-02-14
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
@RequestMapping("/mysql/test")
public class MySQLTestControllerV2 {

    @Autowired
    private MySQLTestServiceV2 mySQLTestServiceV2;

    @PostMapping(value = "/selectV2")
    public List<TestModel> select() throws Exception {
        return mySQLTestServiceV2.select();
    }

    @PostMapping(value = "/insertV2")
    public int insert(@RequestParam(value = "id") int id,
                      @RequestParam(value = "name") String name) throws Exception {
        return mySQLTestServiceV2.insert(id, name);
    }
}
