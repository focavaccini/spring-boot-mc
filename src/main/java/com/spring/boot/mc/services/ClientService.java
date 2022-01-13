package com.spring.boot.mc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.mc.domain.Address;
import com.spring.boot.mc.domain.City;
import com.spring.boot.mc.domain.Client;
import com.spring.boot.mc.domain.enums.CustomerType;
import com.spring.boot.mc.domain.enums.Profile;
import com.spring.boot.mc.dto.ClientDTO;
import com.spring.boot.mc.dto.ClientNewDTO;
import com.spring.boot.mc.repositories.AddressRepository;
import com.spring.boot.mc.repositories.ClientRepository;
import com.spring.boot.mc.security.UserSS;
import com.spring.boot.mc.services.exceptions.AuthorizationException;
import com.spring.boot.mc.services.exceptions.DataIntegrityException;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	public Client findById(Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Client> obj =  clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);	
		
	}

	public void delete(Integer id) {
		findById(id);
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete a client that contains an order");
		}
	}
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Page<Client> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), CustomerType.toEnum(objDto.getType()), bCrypt.encode(objDto.getPassword()));
		City cit = new City(objDto.getCityId(), null, null);
		Address address = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getCep(), client, cit);
		client.getAdresses().add(address);
		client.getPhones().add(objDto.getPhone1());
		
		if(objDto.getPhone2() != null) {
			client.getPhones().add(objDto.getPhone2());
		}
		
		if(objDto.getPhone3() != null) {
			client.getPhones().add(objDto.getPhone3());
		}
		
		return client;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
