package com.spring.boot.mc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.State;
import com.spring.boot.mc.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll(){
		return stateRepository.findAllByOrderByName();
	}

}
