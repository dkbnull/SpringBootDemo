package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.constant.ReturnMessage;
import cn.wbnull.springbootdemo.util.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 登录接口服务类
 *
 * @author dukunbiao(null)  2018-09-23
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class LoginService {

    public JSONObject login(Object data) throws Exception {
        JSONObject requestParams = JSONObject.parseObject(data.toString());
        if (JSONUtils.getJSONString(requestParams, "username").equalsIgnoreCase(
                JSONUtils.getJSONString(requestParams, "password"))) {
            return ReturnMessage.createReturnMessage("2000", "login success");
        } else {
            return ReturnMessage.createReturnMessage("2001", "login fail");
        }
    }
}
