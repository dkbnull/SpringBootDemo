package cn.wbnull.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "登录接口")
public class LoginController extends BaseController {

    @PostMapping(value = "/login")
    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "签名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", required = true, dataType = "String"),
            @ApiImplicitParam(name = "data", value = "请求数据", required = true, dataType = "String")
    })
    public JSONObject login(
            @RequestParam(value = "sign") String sign,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "data") String data
    ) throws Exception {
        return baseService.login(data);
    }
}
