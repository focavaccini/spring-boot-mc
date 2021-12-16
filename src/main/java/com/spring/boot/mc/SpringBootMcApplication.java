package com.spring.boot.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.boot.mc.domain.Category;
import com.spring.boot.mc.domain.Product;
import com.spring.boot.mc.repositories.CategoryRepository;
import com.spring.boot.mc.repositories.ProductRepository;

@SpringBootApplication
public class SpringBootMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Officce");
		Category cat2 = new Category(null, "Computers");
		
		
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Impressora", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		
		
		
		cat1.getProduct().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProduct().addAll(Arrays.asList(p1));
		
		p1.getCategory().addAll(Arrays.asList(cat1));
		p2.getCategory().addAll(Arrays.asList(cat1, cat2));
		p3.getCategory().addAll(Arrays.asList(cat2));
	
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
