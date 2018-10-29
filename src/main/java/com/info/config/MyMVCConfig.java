package com.info.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.info.interceptors.UsersLimitInterceptor;



/**
 * @author vijay
 * Config class for Spring WebMVC ,Used in intercepter to filter application 
 *
 *
 *
 */
@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UsersLimitInterceptor());
	}

}
