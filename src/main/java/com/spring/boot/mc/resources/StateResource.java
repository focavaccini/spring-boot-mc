package com.spring.boot.mc.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mc.domain.City;
import com.spring.boot.mc.domain.State;
import com.spring.boot.mc.dto.CityDTO;
import com.spring.boot.mc.dto.StateDTO;
import com.spring.boot.mc.services.CityService;
import com.spring.boot.mc.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> list = stateService.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
