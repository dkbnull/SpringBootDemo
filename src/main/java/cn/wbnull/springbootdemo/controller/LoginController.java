package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.util.LoggerUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author dukunbiao(null)  2018-09-23
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
public class LoginController extends BaseController {

    @PostMapping(value = "/login")
    public JSONObject login(
            @RequestParam(value = "sign") String sign,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "data") String data
    ) throws Exception {
        LoggerUtils.getLogger().info("[" + Thread.currentThread().getId() + "] LoginController");
        return baseService.login(data);
    }
}
