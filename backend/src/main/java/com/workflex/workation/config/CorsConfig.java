package com.workflex.workation.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
@Configuration public class CorsConfig implements WebMvcConfigurer {
 @Override public void addCorsMappings(CorsRegistry registry){
 registry.addMapping("/workflex/**").allowedOrigins("http://localhost:4200").allowedMethods("GET");
 }
}
