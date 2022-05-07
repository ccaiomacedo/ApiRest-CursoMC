package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.repository.ClientRepository;
import com.caiomacedo.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository repo;

    @Override // é um metodo que vai receber um usuário e retornar um userDetails
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client cli = repo.findByEmail(email);
        if (cli == null){
            throw new UsernameNotFoundException(email);
        }
            return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
    }
}
