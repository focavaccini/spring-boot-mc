package com.spring.boot.mc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mc.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> list() {
		List<Category> list = new ArrayList<>();
		Category cat1 = new Category(1, "Books");
		Category cat2 = new Category(2, "Tools");
		
		list.add(cat1);
		list.add(cat2);
		
		return list;
		
	}
}
