package com.emazon.stockservice.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtils {

    private static final String  SECRET_KEY = "my_secret";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY.getBytes());

    public Boolean isValid(String jwt ){
        try {
            JWT.require(ALGORITHM).build().verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt){
        return JWT.require(ALGORITHM).build().verify(jwt).getSubject();
    }

    public List<String> getRoles(String jwt) {
        return JWT.require(ALGORITHM).build().verify(jwt).getClaim("roles").asList(String.class);
    }
}
