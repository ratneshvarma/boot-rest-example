package com.example.demo.controller;

import com.example.demo.model.AdminUser;
import com.example.demo.security.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    private JwtGenerator jwtGenerator;
    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping
    public String generate(@RequestBody final AdminUser adminUser) {
    	//System.out.println("TOKEN Hello");
        return jwtGenerator.generate(adminUser);

    }
}
