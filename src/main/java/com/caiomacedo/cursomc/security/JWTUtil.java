package com.caiomacedo.cursomc.security;

import io.jsonwebtoken.Claims;
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

    public Boolean tokenValido(String token) {
        Claims claims = getClaims(token);//o claimms armazena as reivindicações do token.Quem envia o token ta alegando que é o usuário tal, pega o tempo de expiração do token. Isso fica armazenado dentro do claimns
        if (claims != null) {
            String username = claims.getSubject(); // getSubject é a função do claims pra retornar um usuário
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis()); // pega a data atual
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return  claims.getSubject();
        }
        return null;
    }

    //essa função recupera os claims a partir de um token
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

        } catch (Exception e) {
            return null;
        }
    }

}
