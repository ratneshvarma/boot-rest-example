package com.example.demo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;

public interface EmployeeService extends CrudRepository<Employee, Integer> {
	
	}
	
