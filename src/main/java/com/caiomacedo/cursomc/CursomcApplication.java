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

	@Autowired
	OrderRepository or;

	@Autowired
	PaymentRepository payr;

	@Autowired
	OrderItemRepository oir;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama,mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getList().addAll(Arrays.asList(p1, p2, p3));
		cat2.getList().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		cr.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
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
		ar.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Orders ped1 = new Orders(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Orders ped2 = new Orders(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new CardPayment(null, PaymentStatus.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Payment pagto2 = new BilletPayment(null, PaymentStatus.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); //associação do cliente com os pedidos

		or.saveAll(Arrays.asList(ped1, ped2));
		payr.saveAll(Arrays.asList(pagto1, pagto2));

		OrderItem ip1 = new OrderItem(ped1,p1,0.00,1,2000.00);
		OrderItem ip2 = new OrderItem(ped1,p3,0.00,2,80.00);
		OrderItem ip3 = new OrderItem(ped2,p2,100.00,1,800.00);

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		oir.saveAll(Arrays.asList(ip1,ip2,ip3));

	}
}
