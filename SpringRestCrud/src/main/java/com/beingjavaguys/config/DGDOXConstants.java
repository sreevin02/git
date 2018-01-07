/***********************************************************************
 * Copyright 2015, www.ideabytes.com					*      
 * Project : DGDOX Service          					*
 * Description : DGDOX constants	*
 ***********************************************************************/
package com.beingjavaguys.config;

public class DGDOXConstants {
	/**
	 * Common constants for dgdox
	 */
	public static final String BASE_PACKAGE = "com.beingjavaguys";


	/**
	 * Swagger related constants
	 */

	public static final String SWAGGER_FILENAME = "swagger-ui.html";
	public static final String SWAGGER_FILEPATH = "classpath:/META-INF/resources/";
	public static final String SWAGGER_WEB_JARS_PATH = "classpath:/META-INF/resources/webjars/";
	public static final String SWAGGER_WEB_JARS = "/webjars/**";
	
	/**
	 * Swagger UI Constants
	 */
	
	public static final String SWAGGER_API_MAPPING = "/app/dgdox/.*";
	public static final String SWAGGER_UI_TITLE = "DGDOX REST APIs";
	public static final String SWAGGER_UI_DESCRIPTION = "DGDOX Service APIs.";
	public static final String SWAGGER_UI_VERSION = "1.0.0";
	public static final String SWAGGER_UI_TERMS_OF_SERVICE_URL = "";
	public static final String SWAGGER_UI_CONTACT = "";
	public static final String SWAGGER_UI_LICENSE = "";
	public static final String SWAGGER_UI_LICENSE_URL = "";
	
	/**
	 * Header Constants
	 */
	public static final String ACCEPT_JSON = "Accept=*/*";


	/**
	 * Error Constants
	 */
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";
	
	
	/**
	 * property configuration file path
	 * 
	 */
	public static final String PROPERTY_FILE_CONFIG = "classpath:/org/com/ideabytes/*.properties";
	
	
	public static final String CONTENT_TYPE = "ContentType";
	

}
