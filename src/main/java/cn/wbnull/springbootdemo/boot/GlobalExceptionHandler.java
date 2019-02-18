package cn.wbnull.springbootdemo.boot;

import cn.wbnull.springbootdemo.constant.ReturnMessage;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author dukunbiao(null)  2018-09-23
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(HttpServletRequest servletRequest, Exception e) {
        LoggerUtils.error(servletRequest.getRemoteAddr(), "中台返回前台", servletRequest.getRequestURI(), e.toString());
        LoggerUtils.error(servletRequest.getRemoteAddr(), "中台返回前台，异常堆栈信息", servletRequest.getRequestURI(), e);

        return ReturnMessage.createReturnMessage("5000", e.getMessage());
    }
}
