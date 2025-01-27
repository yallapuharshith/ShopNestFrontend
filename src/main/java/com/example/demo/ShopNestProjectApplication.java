package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShopNestProjectApplication {

    public static void main(String[] args) {
    	System.setProperty("jwt.secret", "bXq+2Cpae+SyTwMNi1250I4zAwU5Nof/UhqoZ5bnOQHHnvYSb4ccRRk//Ygw+aRTi9v6TxfJeob5ZJBxYTToFA==");
        SpringApplication.run(ShopNestProjectApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
