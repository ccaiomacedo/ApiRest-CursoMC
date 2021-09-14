package com.caiomacedo.cursomc.config;

import com.caiomacedo.cursomc.service.DBService;
import com.caiomacedo.cursomc.service.EmailService;
import com.caiomacedo.cursomc.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")//estou especificando que todos os beans dessa classe só serão ativos quando o profile de dev estiver ativo em application.properties
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
