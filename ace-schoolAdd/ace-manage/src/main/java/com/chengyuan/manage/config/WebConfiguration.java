package com.chengyuan.manage.config;

import com.chengyuan.manage.interceptor.ServiceManageRestInterceptor;
import com.github.wxiaoqi.security.auth.client.interceptor.ServiceAuthRestInterceptor;
import com.github.wxiaoqi.security.auth.client.interceptor.UserAuthRestInterceptor;
import com.github.wxiaoqi.security.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ace
 * @date 2017/9/8
 */
@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns());
    }

//    @Bean
//    ServiceManageRestInterceptor getServiceManageRestInterceptor() {
//        return new ServiceManageRestInterceptor();
//    }
    //用户权限验证
    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
    return new UserAuthRestInterceptor();
}

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }


    /**
     * 需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/**"
//                "/element/**",
//                "/gateLog/**",
//                "/group/**",
//                "/groupType/**",
//                "/menu/**",
//                "/user/**",
//                "/api/permissions",
//                "/api/user/un/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
