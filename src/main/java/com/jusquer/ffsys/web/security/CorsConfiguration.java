package com.jusquer.ffsys.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("PUT", "DELETE","GET","POST","OPTIONS","HEAD")
                .allowCredentials(false)
                .exposedHeaders("Content-Range")
                .allowedHeaders("Content-Type", "Accept","Accept-Encoding","Accept-Language","Connection",
                        "Content-Length","Host","Origin","Referer","Sec-Fetch-Dest","Sec-Fetch-Mode","Sec-Fetch-Site",
                        "User-Agent", "Cache-Control", "X-Requested-With", "Authorization", "Data",
                        "Condition","authorization","sec-ch-ua-platform","sec-ch-ua-mobile","sec-ch-ua","")
                .allowedOrigins("*");
    }
}
