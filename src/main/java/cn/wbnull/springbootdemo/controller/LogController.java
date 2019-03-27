package cn.wbnull.springbootdemo.controller;

import cn.wbnull.springbootdemo.service.LogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志接口
 *
 * @author dukunbiao(null)  2019-03-27
 *         https://github.com/dkbnull/SpringBootDemo
 */
@RestController
@Scope("prototype")
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 下载日志接口
     *
     * @param name
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/download/{name}")
    public void logDownload(@PathVariable String name, HttpServletResponse response) throws Exception {
        logService.logDownload(name, response);
    }

    /**
     * 上传日志接口
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    public JSONObject logUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return logService.logUpload(file);
    }

    /**
     * 批量上传日志接口
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploads")
    public JSONObject logUploads(HttpServletRequest request) throws Exception {
        return logService.logUploads(request);
    }
}
