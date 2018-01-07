/***********************************************************************
 * Copyright 2016, www.ideabytes.com					*      
 * Project : DGDOX Service          					*
 * Description : Spring configuration file to Swagger ui integration	*
 ***********************************************************************/

package com.beingjavaguys.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@ComponentScan(basePackages = DGDOXConstants.BASE_PACKAGE)
@Import(SwaggerConfig.class)
public class SpringConfig extends WebMvcConfigurerAdapter {

	/**
	 * To add resource handlers
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(
				DGDOXConstants.SWAGGER_FILENAME)
				.addResourceLocations(
						DGDOXConstants.SWAGGER_FILEPATH);

		registry.addResourceHandler(
				DGDOXConstants.SWAGGER_WEB_JARS)
				.addResourceLocations(
						DGDOXConstants.SWAGGER_WEB_JARS_PATH);
	}
}
