package org.com.ideabytes.resources.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.com.ideabytes.database.DatabaseConf;
import org.com.ideabytes.prepareresponce.PrepareResponse;
import org.com.ideabytes.resources.common.LibraryConstants;
import org.com.ideabytes.resources.model.Employee;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = LibraryConstants.GETLATESTTRANSACTIONID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?>  getLatestTransactionId() {
		logger.info("GETLATESTTRANSACTIONID");
		String responce = new DatabaseConf().getLatestId("TransId");
		JSONObject jData = new JSONObject();
		Map<String, String> map = new HashMap<>();
		if(responce.equalsIgnoreCase("")){
			map.put(LibraryConstants.STATUS, LibraryConstants.NINE);
			map.put(LibraryConstants.MESSAGE,
					responce); 
			 jData = new PrepareResponse().prepareSuceessResponse(map);
		}else{
			map.put(LibraryConstants.STATUS, LibraryConstants.ZERO);
			map.put(LibraryConstants.MESSAGE,
					responce); 
			jData = new PrepareResponse().prepareSuceessResponse(map);
			
		}
		
		return new ResponseEntity<>(jData.toString(),HttpStatus.OK);
		 
	}
	
	@RequestMapping(value = LibraryConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. ID="+empId);
		
		return empData.get(empId);
	}
	
	@RequestMapping(value = LibraryConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = LibraryConstants.GETBILLOFLADDINGDETAILS, method = RequestMethod.POST)
	public @ResponseBody String getBillofladdingDetails(@RequestBody String reqText) {
		logger.info("GETBILLOFLADDINGDETAILS");
		JSONArray reponceData = new DatabaseConf().GetBillOfLadingDetails(reqText);
		return reponceData.toString();
	}
	
	@RequestMapping(value = LibraryConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
}
