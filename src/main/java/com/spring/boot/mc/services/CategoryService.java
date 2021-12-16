package com.spring.boot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Category;
import com.spring.boot.mc.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category findById(Integer id) {
		Optional<Category> obj =  categoryRepository.findById(id);
		return obj.orElse(null);
	}
}
