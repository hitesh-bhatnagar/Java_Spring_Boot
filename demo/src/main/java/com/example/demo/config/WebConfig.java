package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // marks this as a spring config file
public class WebConfig {
    
    @Bean 
    public WebMvcConfigurer corsConfigurer() {


        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**") // apply to all API endpoints
                    .allowedOrigins("http://localhost:3000")    // specify which frontend can talk to backend
                    .allowedMethods("GET", "POST","PUT","DELETE") // specify allowed HTTP methods
                    .allowCredentials(true) // if frontend uses cookies for authentication
                    .allowedHeaders("*");
            }
        };
    }
}
