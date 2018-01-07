package com.beingjavaguys.model;

public class ServiceInfo {


	private String originalURI;
	private String handler;
    private String version;
    
    public ServiceInfo() {
	}
	public String getOriginalURI() {
		return originalURI;
	}


	public void setOriginalURI(String originalURI) {
		this.originalURI = originalURI;
	}


	public String getHandler() {
		return handler;
	}


	public void setHandler(String handler) {
		this.handler = handler;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}
	public ServiceInfo(String originalURI, String handler, String version) {
		this.originalURI = originalURI;
		this.handler = handler;
		this.version = version;
	}


}
