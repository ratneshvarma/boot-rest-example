package com.example.demo.security;

import com.example.demo.model.AdminUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import static com.example.demo.security.SecurityConstants.SECRET_KEY;
@Component
public class JwtValidator {
	public AdminUser validate(String token) {
     AdminUser adminUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            adminUser = new AdminUser();
            adminUser.setFirstName(body.getSubject());
            adminUser.setUserId(Long.parseLong((String) body.get("userId")));
            adminUser.setUserRole((String) body.get("userRole"));
    }
        catch (Exception e) {
            System.out.println(e);
        }
        return adminUser;
    }
}
