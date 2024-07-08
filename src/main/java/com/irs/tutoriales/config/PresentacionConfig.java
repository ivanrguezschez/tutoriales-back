package com.irs.tutoriales.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PresentacionConfig implements WebMvcConfigurer {

    /*
        Con esto o poniendo la siguiente anotación en el controller o controllers
    
        @CrossOrigin(origins = "http://localhost:4200")
    
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE");
    }
}
