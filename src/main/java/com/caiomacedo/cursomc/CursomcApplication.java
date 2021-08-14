package com.caiomacedo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.caiomacedo.cursomc.domain.Address;
import com.caiomacedo.cursomc.domain.Category;
import com.caiomacedo.cursomc.domain.City;
import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.domain.Product;
import com.caiomacedo.cursomc.domain.State;
import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.caiomacedo.cursomc.repository.AddressRepository;
import com.caiomacedo.cursomc.repository.CategoryRepository;
import com.caiomacedo.cursomc.repository.CityRepository;
import com.caiomacedo.cursomc.repository.ClientRepository;
import com.caiomacedo.cursomc.repository.ProductRepository;
import com.caiomacedo.cursomc.repository.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {// serve para executar uma ação quando a aplicação começar
	@Autowired
	CategoryRepository cr;

	@Autowired
	ProductRepository pr;

	@Autowired
	StateRepository sr;

	@Autowired
	CityRepository cir;
	
	@Autowired
	AddressRepository ar;
	
	@Autowired
	ClientRepository clr;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getList().addAll(Arrays.asList(p1, p2, p3));
		cat2.getList().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		cr.saveAll(Arrays.asList(cat1, cat2));
		pr.saveAll(Arrays.asList(p1, p2, p3));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		sr.saveAll(Arrays.asList(est1, est2));
		cir.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "363453453", ClientType.PESSOAFISICA);

		cli1.getTelefone().addAll(Arrays.asList("434242423", "42423424"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "4234242", cli1, c2);

		cli1.getAddress().addAll(Arrays.asList(e1, e2));
		
		clr.saveAll(Arrays.asList(cli1));
		ar.saveAll(Arrays.asList(e1,e2));
	}
}
