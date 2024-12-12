package com.ecommerce.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
	
		return new BCryptPasswordEncoder();
	}
}
