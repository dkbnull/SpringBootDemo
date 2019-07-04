package cn.wbnull.springbootdemo.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务类路由
 *
 * @author dukunbiao(null)  2018-09-23
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class BaseService implements IService {

    @Autowired
    private LoginService loginService;

    @Override
    public JSONObject login(Object data) throws Exception {
        return loginService.login(data);
    }
}
