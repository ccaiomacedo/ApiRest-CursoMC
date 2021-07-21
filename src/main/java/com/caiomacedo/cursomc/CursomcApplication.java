package com.caiomacedo.cursomc;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {// serve para executar uma ação quando a aplicação começar
	@Autowired
	CategoryRepository cr;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Category cat1 = new Category(null,"Informática");
		Category cat2 = new Category(null,"Escritório");

		cr.saveAll(Arrays.asList(cat1,cat2));

	}
}
