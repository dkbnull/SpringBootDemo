//package cn.wbnull.springbootdemo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * GlobalCorsFilter
// *
// * @author dukunbiao(null)  2024-04-28
// * https://github.com/dkbnull/SpringBootDemo
// */
//@Configuration
//public class GlobalCorsFilter {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        //支持域
//        config.addAllowedOriginPattern("*");
//        //是否发送Cookie
//        config.setAllowCredentials(true);
//        //支持请求方式
//        config.addAllowedMethod("*");
//        //允许的原始请求头部信息
//        config.addAllowedHeader("*");
//        //暴露的头部信息
//        config.addExposedHeader("*");
//
//        //添加地址映射
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        corsConfigurationSource.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(corsConfigurationSource);
//    }
//}
