package com.example.demo.security;

import com.example.demo.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.example.demo.security.SecurityConstants.TOKEN_MISSING_ERROR;
import static com.example.demo.security.SecurityConstants.URL_TO_FILTER;
import static com.example.demo.security.SecurityConstants.HEADER_VALUE;
import static com.example.demo.security.SecurityConstants.TOKEN_PREFIX;
import static com.example.demo.security.SecurityConstants.TOKEN_PREFIX_LENGHT;;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    public JwtAuthenticationTokenFilter() {
        super(URL_TO_FILTER);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String header = httpServletRequest.getHeader(HEADER_VALUE);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException(TOKEN_MISSING_ERROR);
        }
        String authenticationToken = header.substring(TOKEN_PREFIX_LENGHT);
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
