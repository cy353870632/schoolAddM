package com.chengyuan.manage.interceptor;

import com.chengyuan.manage.util.JWTUtil;
import com.chengyuan.manage.util.UserMessage;
import com.github.wxiaoqi.security.auth.client.annotation.IgnoreClientToken;
import com.github.wxiaoqi.security.auth.util.user.JwtTokenUtil;
import com.github.wxiaoqi.security.common.exception.auth.ClientForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ace on 2017/9/12.
 */
@SuppressWarnings("ALL")
public class ServiceManageRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceManageRestInterceptor.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Value("${token-header}")
    private String tokenHeader;

    private List<String> allowedClient;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(tokenHeader);
        if (token==null || token.equals("")){
            throw new ClientForbiddenException("token is Forbidden!");
        }else {
            if (new UserMessage(jwtTokenUtil).getUserId()!=null){
                return super.preHandle(request, response, handler);
            }
        }
//        IJWTInfo infoFromToken = serviceAuthUtil.getInfoFromToken(token);
//        for(String client:serviceAuthUtil.getAllowedClient()){
//            if(client.equals(uniqueName)){
//            }
//        }
        throw new ClientForbiddenException("用户未登陆");
    }
}
