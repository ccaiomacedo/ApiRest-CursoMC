package com.caiomacedo.cursomc.resource;

import com.caiomacedo.cursomc.security.JWTUtil;
import com.caiomacedo.cursomc.security.UserSS;
import com.caiomacedo.cursomc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST) // protegido por autenticação
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) { // passou no filtro de autorização
        UserSS user = UserService.authenticated(); // aqui pega o usuário logado
        String token = jwtUtil.generatedToken(user.getUsername()); // então é gerado um novo token com o usuário logado
        response.addHeader("Authorization", "Bearer " + token); // adiciona o token na resposta da requisição
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
}
