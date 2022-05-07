package com.caiomacedo.cursomc.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;

//Essa é a classe responsável por gerar o token a partir do usuário
@Component //componente, pq pode ser injetado em outras classes
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generatedToken(String username) {
        return Jwts.builder() // esse .builder é o cara que gera um token
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // tempo de expiração do token
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()) // esse aqui é pra dizer como que vai assinar o token, ai passa o secret
                .compact();
    }

}
