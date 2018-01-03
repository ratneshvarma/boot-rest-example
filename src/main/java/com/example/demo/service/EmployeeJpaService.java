package com.example.demo.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeJpaService extends JpaRepository<Employee, Serializable> {

}
