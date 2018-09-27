package cn.wbnull.springbootdemo.boot;

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
    public JSONObject exceptionHandler(HttpServletRequest request, Exception e) {
        LoggerUtils.getLogger().error("[" + Thread.currentThread().getId() + "] " + e.toString());
        LoggerUtils.getLogger().debug("[" + Thread.currentThread().getId() + "] ", e);

        return ReturnMessage.createReturnMessage("4000", e.getMessage());
    }
}
