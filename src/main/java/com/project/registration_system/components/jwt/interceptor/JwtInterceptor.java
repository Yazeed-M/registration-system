package com.project.registration_system.components.jwt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.project.registration_system.components.jwt.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class JwtInterceptor implements HandlerInterceptor{
    @Autowired
    private final JwtUtil jwtUtil;
    public JwtInterceptor(JwtUtil jwtUtil){
        this.jwtUtil= jwtUtil;
    }
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = jwtUtil.extractToken(request);
        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        try {
            Claims claims = jwtUtil.validateToken(token);
            // Optionally set user info in request attribute for controllers later
            request.setAttribute("claims", claims);
            return true; // Token valid, proceed
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid or expired JWT token");
            return false;
        }
    }
}
 
