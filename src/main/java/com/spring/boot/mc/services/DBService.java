package com.spring.boot.mc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.spring.boot.mc.domain.enums.Profile;
import com.spring.boot.mc.repositories.AddressRepository;
import com.spring.boot.mc.repositories.CategoryRepository;
import com.spring.boot.mc.repositories.CityRepository;
import com.spring.boot.mc.repositories.ClientRepository;
import com.spring.boot.mc.repositories.OrderRepository;
import com.spring.boot.mc.repositories.OrderedItemRepository;
import com.spring.boot.mc.repositories.PaymentRepository;
import com.spring.boot.mc.repositories.ProductRepository;
import com.spring.boot.mc.repositories.StateRepository;

@Service
public class DBService {
	
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
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public void instantiatedDatabase() throws ParseException {
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
		Product p4 = new Product(null, "Furadeira", 200.0);
		Product p5 = new Product(null, "Pá", 25.0);
		Product p6 = new Product(null, "Celular", 800.0);
		Product p7 = new Product(null, "Carrinho de controle remoto", 80.0);
		Product p8 = new Product(null, "Livro", 20.0);
		Product p9 = new Product(null, "Caneta", 2.0);
		Product p10 = new Product(null, "Chave inglesa", 25.0);
		Product p11 = new Product(null, "Enxada", 35.0);
		Product p12 = new Product(null, "Carrinho de mão", 180.0);
		
		
		cat1.getProduct().addAll(Arrays.asList(p1, p2, p3, p8, p9));
		cat2.getProduct().addAll(Arrays.asList(p1, p3));
		cat3.getProduct().addAll(Arrays.asList(p4, p5, p10, p11, p12));
		cat4.getProduct().addAll(Arrays.asList(p1, p6));
		cat5.getProduct().addAll(Arrays.asList(p7));
		cat6.getProduct().addAll(Arrays.asList(p8, p9));
		cat7.getProduct().addAll(Arrays.asList(p4, p10));
		cat8.getProduct().addAll(Arrays.asList(p4, p5, p10, p11, p12));
				
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat2));
		p4.getCategories().addAll(Arrays.asList(cat3, cat7, cat8));
		p5.getCategories().addAll(Arrays.asList(cat3, cat8));
		p6.getCategories().addAll(Arrays.asList(cat4));
		p7.getCategories().addAll(Arrays.asList(cat5));
		p8.getCategories().addAll(Arrays.asList(cat1, cat6));
		p9.getCategories().addAll(Arrays.asList(cat1, cat6));
		p10.getCategories().addAll(Arrays.asList(cat3, cat7, cat8));
		p11.getCategories().addAll(Arrays.asList(cat3, cat8));
		p12.getCategories().addAll(Arrays.asList(cat3, cat8));
		
	
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		State st3 = new State(null, "Rio de Janeiro");
		State st4 = new State(null, "Paraná");
		State st5 = new State(null, "Rio Grande do Sul");
		State st6 = new State(null, "Bahia");
		State st7 = new State(null, "Mato Grosso");
		State st8 = new State(null, "Goiás");
		State st9 = new State(null, "Roraima");
		
		City cit1 = new City(null, "Uberlândia", st1);
		City cit2 = new City(null, "São Paulo", st2);
		City cit3 = new City(null, "Campinas", st2);
		City cit4 = new City(null, "Rio de Janeiro", st3);
		City cit5 = new City(null, "Cascavel", st4);
		City cit6 = new City(null, "Porto Alegre", st5);
		City cit7 = new City(null, "Goiânia", st8);
		City cit8 = new City(null, "Campo Grande", st7);
		City cit9 = new City(null, "Dourados", st7);
		City cit10 = new City(null, "Boa Vista", st9);
		City cit11 = new City(null, "Salvador", st6);
		City cit12 = new City(null, "Curitiba", st4);
		
		
		st1.getCities().addAll(Arrays.asList(cit1));
		st2.getCities().addAll(Arrays.asList(cit2, cit3));
		st3.getCities().addAll(Arrays.asList(cit4));
		st4.getCities().addAll(Arrays.asList(cit5, cit12));
		st5.getCities().addAll(Arrays.asList(cit6));
		st6.getCities().addAll(Arrays.asList(cit11));
		st7.getCities().addAll(Arrays.asList(cit8, cit9));
		st8.getCities().addAll(Arrays.asList(cit7));
		st9.getCities().addAll(Arrays.asList(cit10));
		
		stateRepository.saveAll(Arrays.asList(st1, st2, st3, st4, st5, st6, st7, st8, st9));
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3, cit4, cit5, cit6, cit7, cit8, cit9, cit10, cit11, cit12));
		
		Client cli1 = new Client(null, "Fernando Cavaccini", "fernandocavaccini@gmail.com", "38892971026", CustomerType.PHYSICAL_PERSON, bCrypt.encode("1234@"));
		Client cli2 = new Client(null, "José Silva", "focavaccini@gmail.com", "17174212026", CustomerType.PHYSICAL_PERSON, bCrypt.encode("1235@"));
		Client cli3 = new Client(null, "Bernardo Ramos", "barnardo@gmail.com", "95788338000166", CustomerType.LEGAL_PERSON, bCrypt.encode("1236@"));
		Client cli4 = new Client(null, "Lucas Rosa", "lucas@gmail.com", "41908533056", CustomerType.PHYSICAL_PERSON, bCrypt.encode("1237@"));
		Client cli5 = new Client(null, "Pedro Benedito", "pedro@gmail.com", "78624441000151", CustomerType.LEGAL_PERSON, bCrypt.encode("1238@"));
		Client cli6 = new Client(null, "João Lucas", "joao@gmail.com", "07701472088", CustomerType.PHYSICAL_PERSON, bCrypt.encode("1239@"));
		
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		cli2.getPhones().addAll(Arrays.asList("12597812"));
		cli2.addProfile(Profile.ADMIN );
		cli3.getPhones().addAll(Arrays.asList("65489162", "789456321"));
		cli4.getPhones().addAll(Arrays.asList("14785214"));
		cli5.getPhones().addAll(Arrays.asList("93212189", "93838393"));
		cli6.getPhones().addAll(Arrays.asList("52254568"));
		
		Address a1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cit1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Apto 123", "Melissa", "38777012", cli1, cit2);
		Address a3 = new Address(null, "Avenida Brasil", "9952", "Apto 10", "Central", "7892543", cli2, cit3);
		Address a4 = new Address(null, "PIO XII", "1634", "Sala 50", "Industrial", "1254786", cli3, cit4);
		Address a5 = new Address(null, "Tito Muffato", "8764", "Apto 9", "Itália", "9461532", cli3, cit5);
		Address a6 = new Address(null, "Sete de Setembro", "6543", "Apto 2", "Quebec", "1324675", cli4, cit6);
		Address a7 = new Address(null, "Castelo Branco", "134", "Sala 55", "São Gonçalo", "9643721", cli4, cit7);
		Address a8 = new Address(null, "Primeiro de Maio", "432", "Apto 13", "Neva", "03152464", cli4, cit8);
		Address a9 = new Address(null, "Itaquatiaras", "9542", "Sala 13", "Alto Alegre", "91642351", cli5, cit9);
		Address a10 = new Address(null, "Xavantes", "8423", "Apto 12", "Florais", "0123467", cli5, cit10);
		Address a11 = new Address(null, "Avenida Das Torres", "1563", "Apto 52", "Centro", "31679452", cli6, cit11);
		Address a12 = new Address(null, "Avenida 3 de Março", "001", "Apto 00", "Centro", "97641512", cli6, cit12);
		
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));
		cli2.getAdresses().addAll(Arrays.asList(a3));
		cli3.getAdresses().addAll(Arrays.asList(a4, a5));
		cli4.getAdresses().addAll(Arrays.asList(a6, a7, a8));
		cli5.getAdresses().addAll(Arrays.asList(a9, a10));
		cli6.getAdresses().addAll(Arrays.asList(a11, a12));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6));
		addressRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order or1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
		Order or2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli2, a2);
		Order or3 = new Order(null, sdf.parse("20/09/2017 08:22"), cli3, a3);
		Order or4 = new Order(null, sdf.parse("08/10/2017 11:14"), cli4, a4);
		Order or5 = new Order(null, sdf.parse("11/09/2017 02:25"), cli5, a5);
		Order or6 = new Order(null, sdf.parse("29/01/2017 13:21"), cli6, a6);
		Order or7 = new Order(null, sdf.parse("17/03/2017 10:26"), cli1, a7);
		Order or8 = new Order(null, sdf.parse("12/05/2017 19:15"), cli2, a8);
		Order or9 = new Order(null, sdf.parse("15/08/2017 08:08"), cli3, a9);
		Order or10 = new Order(null, sdf.parse("07/09/2017 11:11"), cli4, a10);
		Order or11 = new Order(null, sdf.parse("05/12/2017 02:05"), cli5, a11);
		Order or12 = new Order(null, sdf.parse("01/11/2017 13:00"), cli6, a12);
		
		Payment pay1 = new PaymentWithCard(null, PaymentState.SETTLED, or1, 6);
		or1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithBankSlip(null, PaymentState.PENDING, or2, sdf.parse("20/10/2017 00:00"), null);
		or2.setPayment(pay2);
		
		Payment pay3 = new PaymentWithCard(null, PaymentState.SETTLED, or3, 4);
		or3.setPayment(pay3);
		
		Payment pay4 = new PaymentWithBankSlip(null, PaymentState.PENDING, or4, sdf.parse("09/10/2017 00:00"), null);
		or4.setPayment(pay4);
		
		Payment pay5 = new PaymentWithCard(null, PaymentState.SETTLED, or5, 1);
		or5.setPayment(pay5);
		
		Payment pay6 = new PaymentWithBankSlip(null, PaymentState.PENDING, or6, sdf.parse("30/01/2017 00:00"), null);
		or6.setPayment(pay6);
		
		Payment pay7 = new PaymentWithCard(null, PaymentState.SETTLED, or7, 2);
		or7.setPayment(pay7);
		
		Payment pay8 = new PaymentWithBankSlip(null, PaymentState.PENDING, or8, sdf.parse("12/06/2017 00:00"), null);
		or8.setPayment(pay8);
		
		Payment pay9 = new PaymentWithCard(null, PaymentState.SETTLED, or9, 10);
		or9.setPayment(pay9);
		
		Payment pay10 = new PaymentWithBankSlip(null, PaymentState.PENDING, or10, sdf.parse("08/09/2017 00:00"), null);
		or10.setPayment(pay10);
		
		Payment pay11 = new PaymentWithCard(null, PaymentState.SETTLED, or11, 3);
		or11.setPayment(pay11);
		
		Payment pay12 = new PaymentWithBankSlip(null, PaymentState.PENDING, or12, sdf.parse("02/11/2017 00:00"), null);
		or12.setPayment(pay12);
		
		cli1.getOrders().addAll(Arrays.asList(or1, or7));
		cli2.getOrders().addAll(Arrays.asList(or2, or8));
		cli3.getOrders().addAll(Arrays.asList(or3, or9));
		cli4.getOrders().addAll(Arrays.asList(or4, or10));
		cli5.getOrders().addAll(Arrays.asList(or5, or11));
		cli6.getOrders().addAll(Arrays.asList(or6, or12));
		
		orderRepository.saveAll(Arrays.asList(or1, or2, or3, or4, or5, or6, or7, or8, or9, or10, or11, or12));
		
		paymentRepository.saveAll(Arrays.asList(pay1, pay2, pay3, pay4, pay5, pay6, pay7, pay8, pay9, pay10, pay11, pay12));
		
		OrderedItem oi1 = new OrderedItem(or1, p1, 0.00, 1, 2000.00);
		OrderedItem oi2 = new OrderedItem(or2, p3, 0.00, 2, 80.00);
		OrderedItem oi3 = new OrderedItem(or3, p2, 100.00, 1, 800.00);
		OrderedItem oi4 = new OrderedItem(or4, p4, 10.00, 1, 200.00);
		OrderedItem oi5 = new OrderedItem(or5, p5, 0.00, 2, 25.00);
		OrderedItem oi6 = new OrderedItem(or6, p6, 200.00, 2, 800.00);
		OrderedItem oi7 = new OrderedItem(or7, p7, 0.00, 2, 80.00);
		OrderedItem oi8 = new OrderedItem(or8, p8, 0.00, 3, 20.00);
		OrderedItem oi9 = new OrderedItem(or9, p9, 0.00, 10, 2.00);
		OrderedItem oi10 = new OrderedItem(or10, p10, 5.00, 7, 25.00);
		OrderedItem oi11 = new OrderedItem(or11, p11, 0.00, 2, 35.00);
		OrderedItem oi12 = new OrderedItem(or12, p12, 20.00, 4, 180.00);
		
		or1.getItems().addAll(Arrays.asList(oi1, oi2));
		or2.getItems().addAll(Arrays.asList(oi3));
		or3.getItems().addAll(Arrays.asList(oi3, oi6, oi8));
		or4.getItems().addAll(Arrays.asList(oi1, oi6, oi7));
		or5.getItems().addAll(Arrays.asList(oi4, oi5));
		or6.getItems().addAll(Arrays.asList(oi10, oi11));
		or7.getItems().addAll(Arrays.asList(oi7, oi8));
		or8.getItems().addAll(Arrays.asList(oi10, oi11));
		or9.getItems().addAll(Arrays.asList(oi2, oi4, oi6));
		or10.getItems().addAll(Arrays.asList(oi5));
		or11.getItems().addAll(Arrays.asList(oi9));
		or12.getItems().addAll(Arrays.asList(oi2, oi4, oi12));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		p4.getItems().addAll(Arrays.asList(oi4));
		p5.getItems().addAll(Arrays.asList(oi5));
		p6.getItems().addAll(Arrays.asList(oi6));
		p7.getItems().addAll(Arrays.asList(oi7));
		p8.getItems().addAll(Arrays.asList(oi8));
		p9.getItems().addAll(Arrays.asList(oi9));
		p10.getItems().addAll(Arrays.asList(oi10));
		p11.getItems().addAll(Arrays.asList(oi11));
		p12.getItems().addAll(Arrays.asList(oi12));
		
		orderedItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5, oi6, oi7, oi8, oi9, oi10, oi11, oi12));
	}
}
