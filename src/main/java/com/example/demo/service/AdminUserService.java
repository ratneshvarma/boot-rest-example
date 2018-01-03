package com.example.demo.service;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.AdminUser;

public interface AdminUserService extends CrudRepository<AdminUser, Serializable> {

}
