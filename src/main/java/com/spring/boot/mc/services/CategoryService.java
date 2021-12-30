package com.spring.boot.mc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Category;
import com.spring.boot.mc.dto.CategoryDTO;
import com.spring.boot.mc.repositories.CategoryRepository;
import com.spring.boot.mc.services.exceptions.DataIntegrityException;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category findById(Integer id) {
		Optional<Category> obj =  categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		obj = categoryRepository.save(obj);
		return obj;
	}
	
	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return categoryRepository.save(newObj);	
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			categoryRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a category that contains products");
		}
	}
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Page<Category> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
