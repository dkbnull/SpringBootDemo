package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.service.MySQLTestV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Mysql testv2 控制类
 *
 * @author dukunbiao(null)  2019-02-14
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
@RequestMapping("/mysql/testv2")
@ApiIgnore
public class MySQLTestV2Controller {

    @Autowired
    private MySQLTestV2Service mySQLTestV2Service;

    @PostMapping(value = "/insert")
    public int insert(@RequestParam(value = "id") int id,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password
    ) throws Exception {
        return mySQLTestV2Service.insert(id, username, password);
    }
}
