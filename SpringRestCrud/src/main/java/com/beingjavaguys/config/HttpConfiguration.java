/****************************************************************
 * Copyright 2016, www.ideabytes.com					*      
 * Project : DGDOX Service          				*
 * Description : From server-sample  						    *
 ****************************************************************/
package com.beingjavaguys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = DGDOXConstants.BASE_PACKAGE)
public class HttpConfiguration extends WebMvcConfigurationSupport {
	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = super
				.requestMappingHandlerMapping();
		// Set to false to allow dots (.) in url paths
		mapping.setUseSuffixPatternMatch(false);
		mapping.setUseRegisteredSuffixPatternMatch(false);
		return mapping;
	}

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		// just get returned type by HTTP 'Accept' header
		configurer.favorPathExtension(false).favorParameter(false);
	}

}
