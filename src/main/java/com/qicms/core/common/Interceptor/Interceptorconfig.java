package com.qicms.core.common.Interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

//配置拦截器
@Configuration
public class Interceptorconfig extends WebMvcConfigurerAdapter{

    @Value("${file.root.path}")
    private String fileRoot;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	 registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/logins").excludePathPatterns("/captcha").excludePathPatterns("/vVault/**").excludePathPatterns("/zOps/**").excludePathPatterns("/x7Stat/**").excludePathPatterns("/qTpl/**").excludePathPatterns("/pDossier/**").excludePathPatterns("/sInject/**").excludePathPatterns("/upload/**");
//        registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/index").excludePathPatterns("/test2");

    }

    //上传附件对外访问路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = new File(fileRoot).getAbsolutePath().replace("\\", "/");
        if (!location.endsWith("/")) {
            location = location + "/";
        }
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + location);
    }
}