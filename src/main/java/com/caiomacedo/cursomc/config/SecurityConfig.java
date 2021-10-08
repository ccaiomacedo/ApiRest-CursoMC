package com.caiomacedo.cursomc.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final String[] PUBLIC_MATCHERS = {//aqui eu vou definir quais caminhos por padrão irão estar liberados
            "/h2-console/**",
    };
    private static final String[] PUBLIC_MATCHERS_GET = {//aqui eu vou definir quais caminhos por padrão irão estar liberados
            "/products/**",
            "/categories/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){//está pegando o profile, e caso ele seja o test ai vai executar o comando de baixo.
            http.headers().frameOptions().disable();//esse comando está liberando o acesso ao h2
        }

        http.cors().and().csrf().disable();//está desabilitando a proteção de ataque csrf, o ataque é baseado no armazenamento da autenticação e sessão, só que n vai ser usado sessão
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()//só vai permitir o metodo get pros caras que tiverem na lista PUBLIC_MATCHERS_GET
                .antMatchers(PUBLIC_MATCHERS).permitAll()//aqui estou dizendo que todos os caminhos que estiverem no vetor PUBLIC_MATCHERS, irão ser permitidos
         .anyRequest().authenticated();//e para o resto exige autenticação
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//para assegurar que o backend não vai criar sessão de usuário
    }

    @Bean//para que eu possa injetar em qualquer classe do sistema
    CorsConfigurationSource corsConfigurationSource(){//se tiver um método cors definido, essas configurações serão aplicadas
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());//estou permitindo acesso ao meu endPoint com as configurações básicas
        return source;
    }

    @Bean//para que eu possa injetar em qualquer classe do sistema
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
