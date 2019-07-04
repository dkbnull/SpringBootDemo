package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author dukunbiao(null)  2018-09-23
 * https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
public class LoginController {

    @PostMapping(value = "/login")
    public String login(
            @RequestParam(value = "sign") String sign,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "data") String data
    ) throws Exception {
        String strLog = "[" + Thread.currentThread().getId() + "]" + "[请求方法] " + "/login" + " ||";
        LoggerUtils.getLogger().info(strLog + "[请求参数] sign=" + sign + ",timestamp=" + timestamp + ",data=" + data);

        return "SUCCESS";
    }
}
