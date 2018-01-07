/****************************************************************
 * Copyright 2016, www.ideabytes.com				*      
 * Project : DGDOX Service          				*
 * Description : ApplicationInitializer for Swagger integration *
 ****************************************************************/

package com.beingjavaguys.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * To get root configuration
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/**
	 * To get servlet configuration
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringConfig.class };
	}

	/**
	 * To get servlet mappings
	 */
	@Override
	protected String[] getServletMappings() {
	
		return new String[] { "/*" };
	}
}
