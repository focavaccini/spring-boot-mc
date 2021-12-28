package com.spring.boot.mc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.boot.mc.domain.Address;
import com.spring.boot.mc.domain.Category;
import com.spring.boot.mc.domain.City;
import com.spring.boot.mc.domain.Client;
import com.spring.boot.mc.domain.Order;
import com.spring.boot.mc.domain.OrderedItem;
import com.spring.boot.mc.domain.Payment;
import com.spring.boot.mc.domain.PaymentWithBankSlip;
import com.spring.boot.mc.domain.PaymentWithCard;
import com.spring.boot.mc.domain.Product;
import com.spring.boot.mc.domain.State;
import com.spring.boot.mc.domain.enums.CustomerType;
import com.spring.boot.mc.domain.enums.PaymentState;
import com.spring.boot.mc.repositories.AddressRepository;
import com.spring.boot.mc.repositories.CategoryRepository;
import com.spring.boot.mc.repositories.CityRepository;
import com.spring.boot.mc.repositories.ClientRepository;
import com.spring.boot.mc.repositories.OrderRepository;
import com.spring.boot.mc.repositories.OrderedItemRepository;
import com.spring.boot.mc.repositories.PaymentRepository;
import com.spring.boot.mc.repositories.ProductRepository;
import com.spring.boot.mc.repositories.StateRepository;

@SpringBootApplication
public class SpringBootMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Office");
		Category cat2 = new Category(null, "Computers");
		Category cat3 = new Category(null, "Tools");
		Category cat4 = new Category(null, "Eletronics");
		Category cat5 = new Category(null, "Toys");
		Category cat6 = new Category(null, "School");
		Category cat7 = new Category(null, "Mechanics");
		Category cat8 = new Category(null, "Construction");
		
		
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Impressora", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		
		
		
		cat1.getProduct().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProduct().addAll(Arrays.asList(p1));
		
		p1.getCategory().addAll(Arrays.asList(cat1));
		p2.getCategory().addAll(Arrays.asList(cat1, cat2));
		p3.getCategory().addAll(Arrays.asList(cat2));
		
	
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City cit1 = new City(null, "Uberlândia", st1);
		City cit2 = new City(null, "São Paulo", st2);
		City cit3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(cit1));
		st2.getCities().addAll(Arrays.asList(cit2, cit3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.PHYSICAL_PERSON);
		
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address a1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cit1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cit2);
		
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order or1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
		Order or2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, a2);
		
		Payment pay1 = new PaymentWithCard(null, PaymentState.SETTLED, or1, 6);
		or1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithBankSlip(null, PaymentState.PENDING, or2, sdf.parse("20/10/2017 00:00"), null);
		or2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(or1, or2));
		
		orderRepository.saveAll(Arrays.asList(or1, or2));
		
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderedItem oi1 = new OrderedItem(or1, p1, 0.00, 1, 2000.00);
		OrderedItem oi2 = new OrderedItem(or1, p3, 0.00, 2, 80.00);
		OrderedItem oi3 = new OrderedItem(or2, p2, 100.00, 1, 800.00);
		
		or1.getItems().addAll(Arrays.asList(oi1, oi2));
		or2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderedItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}

}
