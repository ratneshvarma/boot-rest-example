package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
	@Autowired
   private EmployeeService employeeService;
	
	@RequestMapping(value= "/employees/{id}", method= RequestMethod.GET )
    public ResponseEntity<Employee> getEmp(@PathVariable("id") int id ) {
		Employee employee=employeeService.findOne(id);
		if(employee!=null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
			
		else
			return new ResponseEntity<Employee>(employee,HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value= "/employees", method=RequestMethod.GET)
	//@RequestMapping(value= "/all", method=RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<Employee>> getAll( ) {
		List<Employee> list= (List<Employee>)employeeService.findAll();
		if(list!=null)
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<List<Employee>>(list, HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value= "/employees", method=RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Employee employee) {
		boolean exist= employeeService.exists(employee.getId());
		Employee employee2 = employeeService.findOne(employee.getId());
		if(exist)
			return new ResponseEntity<Employee>(employee2, HttpStatus.ALREADY_REPORTED);
		else {
		employeeService.save(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(value= "/employees", method=RequestMethod.PUT)
	public ResponseEntity<Employee> update(@RequestBody Employee employee) {
		boolean exist= employeeService.exists(employee.getId());
		if(exist) {
			employeeService.save(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	     }
		else {
			employeeService.save(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}	
	}
	@RequestMapping(value= "/employees/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		boolean exist= employeeService.exists(id);
		Employee employee = employeeService.findOne(id); 
		
		if(exist)
		{
		employeeService.delete(id);		
		return	new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
		}
		else
		return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
	}
		

}
