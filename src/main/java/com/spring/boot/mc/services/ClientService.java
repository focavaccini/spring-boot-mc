package com.spring.boot.mc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Client;
import com.spring.boot.mc.repositories.ClientRepository;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public Client findById(Integer id) {
		Optional<Client> obj =  clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Client.class.getName()));
	}
}
