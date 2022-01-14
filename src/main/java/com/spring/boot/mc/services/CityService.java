package com.spring.boot.mc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.City;
import com.spring.boot.mc.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findByState(Integer stateId){
		return cityRepository.findCities(stateId);
	}
}
