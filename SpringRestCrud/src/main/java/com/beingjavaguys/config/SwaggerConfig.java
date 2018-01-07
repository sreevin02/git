/***********************************************************************
 * Copyright 2016, www.ideabytes.com					*      
 * Project : DGDOX Service          			                *
 * Description : Swagger configuration file to Swagger ui integration	*
 ***********************************************************************/

package com.beingjavaguys.config;

import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Returns docket for matched apis
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors
						.regex(DGDOXConstants.SWAGGER_API_MAPPING))
				.build().apiInfo(apiInfo());
	}

	/**
	 * To set API information
	 * @return
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				DGDOXConstants.SWAGGER_UI_TITLE,
				DGDOXConstants.SWAGGER_UI_DESCRIPTION,
				DGDOXConstants.SWAGGER_UI_VERSION,
				DGDOXConstants.SWAGGER_UI_TERMS_OF_SERVICE_URL,
				DGDOXConstants.SWAGGER_UI_CONTACT,
				DGDOXConstants.SWAGGER_UI_LICENSE,
				DGDOXConstants.SWAGGER_UI_LICENSE_URL);
		return apiInfo;
	}
}
