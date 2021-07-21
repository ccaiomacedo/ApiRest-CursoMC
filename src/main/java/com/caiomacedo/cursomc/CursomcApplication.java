package com.caiomacedo.cursomc;

import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.Product;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import com.caiomacedo.cursomc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {// serve para executar uma ação quando a aplicação começar
	@Autowired
	CategoryRepository cr;

	@Autowired
	ProductRepository pr;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Category cat1 = new Category(null,"Informática");
		Category cat2 = new Category(null,"Escritório");

		Product p1 = new Product(null,"Computador",2000.00);
		Product p2 = new Product(null,"Impressora",800.00);
		Product p3 = new Product(null,"Mouse",80.00);

		cat1.getList().addAll(Arrays.asList(p1,p2,p3));
		cat2.getList().addAll(Arrays.asList(p2));

		p1.getList().addAll(Arrays.asList(cat1));
		p2.getList().addAll(Arrays.asList(cat1,cat2));
		p3.getList().addAll(Arrays.asList(cat1));

		cr.saveAll(Arrays.asList(cat1,cat2));
		pr.saveAll(Arrays.asList(p1,p2,p3));



	}
}
