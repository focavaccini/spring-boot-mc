package com.spring.boot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Order;
import com.spring.boot.mc.repositories.OrderRepository;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository categoryRepository;
	
	public Order findById(Integer id) {
		Optional<Order> obj =  categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Order.class.getName()));
	}
}
