package com.example.demo.config;

import com.example.demo.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter; // Let Spring inject the dependencies into this filter

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistration() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        
        // Register the AuthenticationFilter
        registrationBean.setFilter(authenticationFilter);
        
        // Define URL patterns that the filter should apply to
        registrationBean.addUrlPatterns("/api/*", "/admin/*");  // You can adjust this based on your needs
        
        // Set order of the filter if necessary (default is 1)
        registrationBean.setOrder(1);  // Optional: Use this to control the order of filters
        
        return registrationBean;
    }
}
