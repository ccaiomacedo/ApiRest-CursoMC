package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // retorna o usuário logado no sistema.
        } catch (Exception e) {
            return null;
        }

    }
}
