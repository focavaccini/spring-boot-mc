package com.spring.boot.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mc.domain.Order;
import com.spring.boot.mc.services.OrderService;

@RestController
@RequestMapping(value = "/requests")
public class OrderResource {
	
	@Autowired
	OrderService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Order obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
}