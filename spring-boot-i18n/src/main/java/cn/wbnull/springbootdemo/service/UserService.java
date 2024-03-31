package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.util.I18nUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * UserService
 *
 * @author dukunbiao(null)  2024-03-28
 * https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class UserService {

    public String login() {
        return I18nUtils.getMessage("login.error");
    }

    public String loginSuccess(String username) {
        return I18nUtils.getMessage("login.success", username, new Date());
    }
}
