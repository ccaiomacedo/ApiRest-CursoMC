package com.caiomacedo.cursomc.security;

import com.caiomacedo.cursomc.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
//essa classe é a de filtro de autenticação. Ela que vai fazer a autenticação do usuário passado
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter { // essa implementação faz com que esse filtro intercepte a requisição de login (/login) padrão do spring security

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            CredenciaisDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), CredenciaisDTO.class); // aqui pega os dados que vieram na requisição de login e converte para o CredenciaisDTO.class

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken); //metodo que verifica se o usuário e senha são validos
            return auth;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //ele acrescenta um token  e gera na resposta da requisição
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String username = ((UserSS) auth.getPrincipal()).getUsername();// pego o usuário do spring security dou um casting pra o UserSS e pego o email autenticado
        String token = jwtUtil.generatedToken(username);//gero um token a partir do email autenticado
        res.addHeader("Authorization", "Bearer " + token);// retorna o token no header
        res.addHeader("access-control-expose-headers", "Authorization");
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setStatus(401);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\":" + date + "," + "\"status\":401,"
                    + "\"erro\":Não autorizado\"," + "\"message\":\"Email ou senha inválidos\","
                    + "\"path\":\"/login\"}";
        }
    }
}
