package com.briup.apps.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public JwtInterceptor jwtInterceptor(){
		return new JwtInterceptor();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor())
//				.addPathPatterns("/**")
				/*.excludePathPatterns("/swagger-resources/**","/v2/**","swagger-ui.html","/webjars/**",
						"/user/login","/user/info","/user/logout","/article/download");*/
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","PUT","OPTIONS","DELETE","PATCH")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
	}

}
