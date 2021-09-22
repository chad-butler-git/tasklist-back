package org.securedev.tasklistback.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/tasks").setViewName("tasks");
        registry.addViewController("/").setViewName("tasks");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/tasks/create").setViewName("create");
        registry.addViewController("/tasks/show").setViewName("show");
        registry.addViewController("/tasks/edit").setViewName("edit");
    }

}
