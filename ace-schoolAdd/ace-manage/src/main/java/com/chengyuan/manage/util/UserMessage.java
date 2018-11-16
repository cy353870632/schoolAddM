package com.chengyuan.manage.util;

import com.github.wxiaoqi.security.auth.common.util.jwt.IJWTInfo;
import com.github.wxiaoqi.security.auth.util.user.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chengyuan
 * @create 2018-05-24 16:53
 **/
@Service
public class UserMessage {

    @Autowired
    JWTUtil jwtUtil;


    private JwtTokenUtil jwtTokenUtil;


    public UserMessage(){

    }
    public UserMessage(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String getUserId() throws Exception{
        String token =getHttpServletRequest().getHeader("Authorization");
//        Claims claims = new JWTUtil().parseJWT(token);
        IJWTInfo ijwtInfo = jwtTokenUtil.getInfoFromToken(token);
        return ijwtInfo.getId();
    }
    public static String getUserCode() throws Exception{
        String token =getHttpServletRequest().getHeader("Authorization");
        Claims claims = new JWTUtil().parseJWT(token);
        return claims.get("user_code", String.class);
    }
    public static String getUserName() throws Exception{
        String token =getHttpServletRequest().getHeader("Authorization");
        Claims claims = new JWTUtil().parseJWT(token);
        return claims.get("user_name", String.class);
    }
    public static String getUserRole() throws Exception{
        String token =getHttpServletRequest().getHeader("Authorization");
        Claims claims = new JWTUtil().parseJWT(token);
        return claims.get("user_role", String.class);
    }

    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        return request;
    }

}
