package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 控制器基类
 *
 * @author dukunbiao(null)  2018-09-23
 *         https://github.com/dkbnull/SpringBootDemo
 */
public class BaseController {

    @Autowired
    protected BaseService baseService;
}
