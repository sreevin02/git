package com.beingjavaguys.model;

public class PrepareResponce {

	private String status;
	private Object results;
    private String statusMessage;
    private Object service = "";
	public PrepareResponce() {
	}
	public PrepareResponce(String status, Object results, String statusMessage) {
		this.status = status;
		this.results = results;
		//this.service = new ServiceInfo("DGDOX_WS","DGDO_WS/","1.4.6.749");
		this.statusMessage = statusMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}


	public String getstatus() {
		return status;
	}
	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}
}
