package com.spring.boot.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.boot.mc.domain.Category;
import com.spring.boot.mc.repositories.CategoryRepository;

@SpringBootApplication
public class SpringBootMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Books");
		Category cat2 = new Category(null, "Tools");
		Category cat3 = new Category(null, "Eletronics");
		Category cat4 = new Category(null, "Computers");
		Category cat5 = new Category(null, "Toys");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
	}

}
