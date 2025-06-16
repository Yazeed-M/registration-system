package com.project.registration_system.configs.jwt;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.registration_system.components.jwt.interceptor.JwtInterceptor;
    
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
    
        @Autowired
        private JwtInterceptor jwtInterceptor;
    
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // Apply interceptor to all secured paths, e.g., /api/** or customize
            registry.addInterceptor(jwtInterceptor)
                    .addPathPatterns("/api/v1/private/**");
        }// exclude public endpoints if any
        }
    
    

