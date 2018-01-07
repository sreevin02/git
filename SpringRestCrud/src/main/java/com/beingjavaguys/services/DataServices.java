package com.beingjavaguys.services;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.beingjavaguys.model.Employee;
@Configuration
public interface DataServices {
	public boolean addEntity(Employee employee) throws Exception;
	public Employee getEntityById(long id) throws Exception;
	public List<Employee> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
