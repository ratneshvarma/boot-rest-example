package com.example.demo.security;

import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.example.demo.security.SecurityConstants.SECRET_KEY;

import java.sql.Date;

import static com.example.demo.security.SecurityConstants.EXPIRATION_TIME;

@Component
public class JwtGenerator {
	@Autowired
	private AdminUserService adminUserService;
    public String generate(AdminUser adminUser) {
        Claims claims = Jwts.claims()
                .setSubject(adminUser.getFirstName());
        claims.put("userId", String.valueOf(adminUser.getUserId()));
        claims.put("userRole", adminUser.getUserRole());
        
        AdminUser adminUser1 = adminUserService.findOne(adminUser.getUserId());
        if(adminUser1!=null && adminUser1.getUserRole().equals("admin") ){
        return Jwts.builder()
        		.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        }
        else
        	return null;
    }
}
