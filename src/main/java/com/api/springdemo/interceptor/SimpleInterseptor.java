package com.api.springdemo.interceptor;

import com.api.springdemo.util.validation.JwtUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SimpleInterseptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("register")) {
//            return true;
//        }
//        String token = request.getHeader("Authorization");
//        if (token == null) throw new RuntimeException("Token is not found");
//        String[] bearerToken = token.split(" ");
//        return jwtUtil.validateToken(bearerToken[1]);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        System.out.println("POST HANDLE");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception e) {
        System.out.println("Complate");
    }



}
