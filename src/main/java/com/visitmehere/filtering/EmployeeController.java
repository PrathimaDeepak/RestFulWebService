package com.visitmehere.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class EmployeeController {
	
	@GetMapping("/employee-details")
	public MappingJacksonValue retrieveEmployee() {
		Employee employee = new Employee(1, "Anna", "Manager", 50000);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name", "designation");
		FilterProvider filters = new SimpleFilterProvider().addFilter("employeeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(employee);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/all-employee-details")
	public MappingJacksonValue retrieveAllEmployees() {
		List<Employee> list = Arrays.asList(new Employee(1, "Anna", "Manager", 50000), new Employee(2, "Ben", "Team Lead", 30000));
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "designation");
		FilterProvider filters = new SimpleFilterProvider().addFilter("employeeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
}
