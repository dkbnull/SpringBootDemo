package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.model.TestModel;
import cn.wbnull.springbootdemo.service.MySQLTestService;
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
public class MySQLTestController {

    @Autowired
    private MySQLTestService mySQLTestService;

    @PostMapping(value = "/select")
    public List<TestModel> select() throws Exception {
        return mySQLTestService.select();
    }

    @PostMapping(value = "/insert")
    public int insert(@RequestParam(value = "name") String name) throws Exception {
        return mySQLTestService.insert(name);
    }
}
