package com.spring.boot.mc.services;

import org.springframework.mail.SimpleMailMessage;

import com.spring.boot.mc.domain.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
