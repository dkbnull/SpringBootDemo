package cn.wbnull.springbootdemo.service;

import cn.wbnull.springbootdemo.boot.GlobalException;
import cn.wbnull.springbootdemo.model.ReturnMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 日志接口服务类
 *
 * @author dukunbiao(null)  2019-03-27
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Service
public class LogService {

    public void logDownload(String name, HttpServletResponse response) throws Exception {
        File file = new File("logs" + File.separator + name);

        if (!file.exists()) {
            throw new GlobalException(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    public JSONObject logUpload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new GlobalException("未选择需上传的日志文件");
        }

        String filePath = new File("logs_app").getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        fileUpload = new File(filePath, file.getOriginalFilename());
        if (fileUpload.exists()) {
            throw new GlobalException("上传的日志文件已存在");
        }

        try {
            file.transferTo(fileUpload);

            return ReturnMessage.success();
        } catch (IOException e) {
            throw new GlobalException("上传日志文件到服务器失败：" + e.toString());
        }
    }

    public JSONObject logUploads(HttpServletRequest request) throws Exception {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        for (MultipartFile file : files) {
            logUpload(file);
        }

        return ReturnMessage.success();
    }
}
