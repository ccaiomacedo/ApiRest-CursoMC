package com.caiomacedo.cursomc.config;

import com.caiomacedo.cursomc.domain.BilletPayment;
import com.caiomacedo.cursomc.domain.CardPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//estou configurando o jackson pra poder consumir com o angular
@Configuration // Código de exigência do jackson
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(BilletPayment.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}

