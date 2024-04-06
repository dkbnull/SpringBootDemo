package cn.wbnull.springbootdemo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * 日志接口测试类
 *
 * @author dukunbiao(null)  2019-03-27
 * https://github.com/dkbnull/SpringBootDemo
 */
public class LogControllerTests {

    @Test
    public void logUpload() throws Exception {
        String url = "http://127.0.0.1:8090/springbootdemo/log/upload";
        String pathname = new File("../logs" + File.separator + "log_20190310.log").getCanonicalPath();
        logUpload(url, pathname);
    }

    private static void logUpload(String url, String pathname) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        try {
            FilePart filePart = new FilePart("file", new File(pathname));
            Part[] parts = {filePart};

            MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, new HttpMethodParams());
            postMethod.setRequestEntity(multipartRequestEntity);
            httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
    }
}
