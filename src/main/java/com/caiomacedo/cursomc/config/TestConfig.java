package com.caiomacedo.cursomc.config;

import com.caiomacedo.cursomc.service.DBService;
import com.caiomacedo.cursomc.service.EmailService;
import com.caiomacedo.cursomc.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")//estou especificando que todos os beans dessa classe só serão ativos quando o profile de teste estiver ativo em application.properties
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean//disponível como componente no sistema, para caso eu faça alguma instância ele retorne o método
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }
    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
