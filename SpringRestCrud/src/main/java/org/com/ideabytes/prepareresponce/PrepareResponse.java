package org.com.ideabytes.prepareresponce;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
public class PrepareResponse {
public JSONObject response =null;
public JSONObject prepareSuceessResponse(Map<String,Object> map)throws JSONException{
	response = new JSONObject();
	response.put("service", "");
	response.put("status",00);
	response.put("results", new JSONObject(map));
	response.put("statusMessage","OK");
	 return response;
}
public JSONObject prepareErrorResponse(String exception)throws JSONException{
	response = new JSONObject();
	response.put("service", "");
	response.put("status",50);
	response.put("statusMessage",exception);
	 return response;
}
}
