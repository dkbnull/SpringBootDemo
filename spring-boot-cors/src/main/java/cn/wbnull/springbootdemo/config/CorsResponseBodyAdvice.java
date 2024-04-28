//package cn.wbnull.springbootdemo.config;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * CorsResponseBodyAdvice
// *
// * @author dukunbiao(null)  2024-04-28
// * https://github.com/dkbnull/SpringBootDemo
// */
//@ControllerAdvice
//public class CorsResponseBodyAdvice implements ResponseBodyAdvice {
//
//    /**
//     * 内容是否需要重写
//     * 可以选择部分控制器和方法进行重写
//     * <p>
//     * true 重写
//     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        //设置跨域
//        response.getHeaders().set("Access-Control-Allow-Origin", "*");
//
//        return body;
//    }
//}
