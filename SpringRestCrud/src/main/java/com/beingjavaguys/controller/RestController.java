package com.beingjavaguys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.com.ideabytes.prepareresponce.PrepareResponse;
import org.com.ideabytes.resources.common.LibraryConstants;
import org.json.JSONArray;
import org.json.JSONObject;

import com.beingjavaguys.model.PrepareResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beingjavaguys.config.DGDOXConstants;
import com.beingjavaguys.model.Employee;
import com.beingjavaguys.services.DataServices;
import com.google.gson.Gson;
@ComponentScan({ "com.beingjavaguys" })
@Controller
@RequestMapping("/employee")
public class RestController {

	@Autowired
	DataServices dataServices;

	static final Logger logger = Logger.getLogger(RestController.class);
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	PrepareResponce addEmployee(@RequestBody String employee) {
		try {
			JSONArray inputData = new JSONArray(employee);
			for(int i = 0;i<inputData.length();i++){
				ObjectMapper mapper = new ObjectMapper();
				Employee obj = mapper.readValue(inputData.getJSONObject(i).toString(), Employee.class);
				logger.info("Result  "+dataServices.addEntity(obj));
			}
			
			return new PrepareResponce("00","Employee added Successfully !","Ok");
		} catch (Exception e) {
			// e.printStackTrace();
			return new PrepareResponce("99","Employee added Fail !","FAIL");
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") long id) {
		Employee employee = null;
		try {
			employee = dataServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<?> getEmployee() {
		JSONObject jData = new JSONObject();
		List<Employee> employeeList = null;
		try {
			Map<String, Object> map = new HashMap<>();
			employeeList = dataServices.getEntityList();
			JSONArray jArray = new JSONArray();
			 Gson gson = new Gson();
			for(Employee e : employeeList){
				jArray.put(new JSONObject(gson.toJson(e)) );
			}
			logger.info("GETLATESTTRANSACTIONID  "+jArray.toString());
			if(employeeList.size() == 0){
				map.put(LibraryConstants.STATUS, LibraryConstants.NINE);
				map.put(LibraryConstants.MESSAGE,
						jArray); 
				 jData = new PrepareResponse().prepareSuceessResponse(map);
			}else{
				map.put(LibraryConstants.STATUS, LibraryConstants.ZERO);
				map.put(LibraryConstants.MESSAGE,
						jArray); 
				//jData = new PrepareResponse().prepareSuceessResponse(map);
				
			}
			return new ResponseEntity<>(jData,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

		
		//return new PrepareResponce("00",employeeList,"Ok");

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	PrepareResponce deleteEmployee(@PathVariable("id") long id) {

		try {
			dataServices.deleteEntity(id);
			return new PrepareResponce("00","Employee Deleted","Ok");
		} catch (Exception e) {
			return new PrepareResponce("99","NotDeleted","Fail");
		}

	}
}
