package com.yingsu.jxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.yingsu.jxc.mapper")
@EnableCaching
@EnableAutoConfiguration
public class DemoApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
         MultipartConfigFactory factory = new MultipartConfigFactory();
         //单个文件最大
         factory.setMaxFileSize("10240KB"); //KB,MB
         // 设置总上传数据总大小
         factory.setMaxRequestSize("102400KB");
         return factory.createMultipartConfig();
    }
}