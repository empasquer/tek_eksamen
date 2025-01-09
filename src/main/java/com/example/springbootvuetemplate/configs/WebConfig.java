package com.example.springbootvuetemplate.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE, INSERT")
                .allowedHeaders("*")
                .allowCredentials(true) //Enable credentials (cookies)
                .maxAge(3600); //Cache for 1 hour
    }
}

