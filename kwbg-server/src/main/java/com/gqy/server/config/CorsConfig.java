package com.gqy.server.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ice_water
 * @Date: 2022/02/27/5:25 PM
 * @Description: 要做耿沁园的男人
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        // 1允许任何域名使用
//        corsConfiguration.addAllowedHeader("*");
//        // 2允许任何头
//        corsConfiguration.addAllowedMethod("*");
//
//        // 3允许任何方法（post、get等）
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        // 4处理所有 请求的跨域配置
//        return new CorsFilter(source);
//    }
//}

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}