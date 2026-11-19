package com.qicms.core.common.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//配置拦截器
@Configuration
public class Interceptorconfig extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	 registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/logins").excludePathPatterns("/captcha").excludePathPatterns("/vVault/**").excludePathPatterns("/zOps/**").excludePathPatterns("/x7Stat/**").excludePathPatterns("/qTpl/**").excludePathPatterns("/pDossier/**").excludePathPatterns("/sInject/**").excludePathPatterns("/upload/**");
//        registry.addInterceptor(new recordInterceptor()).addPathPatterns("/**").excludePathPatterns("/logins").excludePathPatterns("/index").excludePathPatterns("/test2");
    
    }
}