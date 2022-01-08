package com.spring.boot.mc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Client;
import com.spring.boot.mc.repositories.ClientRepository;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		
		if(client == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		client.setPassword(bCrypt.encode(newPass));
		
		clientRepository.save(client);
		
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i < 9; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {
			return (char) (rand.nextInt(10) + 48); // gera um número de 0 a 9
		}else if(opt == 1) {
			return (char) (rand.nextInt(26) + 65); // gera uma letra maiúscula de A - Z 
		}else {
			return (char) (rand.nextInt(26) + 97); // gera uma letra minúscula de A - Z 
		}
	}
}
