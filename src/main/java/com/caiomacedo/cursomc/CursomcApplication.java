package com.caiomacedo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.caiomacedo.cursomc.domain.*;
import com.caiomacedo.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.caiomacedo.cursomc.domain.enums.PaymentStatus;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {// serve para executar uma ação quando a aplicação começar

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
