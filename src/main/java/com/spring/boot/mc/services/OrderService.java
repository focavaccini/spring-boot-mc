package com.spring.boot.mc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.Order;
import com.spring.boot.mc.domain.OrderedItem;
import com.spring.boot.mc.domain.PaymentWithBankSlip;
import com.spring.boot.mc.domain.enums.PaymentState;
import com.spring.boot.mc.repositories.OrderRepository;
import com.spring.boot.mc.repositories.OrderedItemRepository;
import com.spring.boot.mc.repositories.PaymentRepository;
import com.spring.boot.mc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SlipBankService slipBankService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order findById(Integer id) {
		Optional<Order> obj =  orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id " + id + ", Type: " + Order.class.getName()));
	}
	
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.getPayment().setPaymentState((PaymentState.PENDING));
		obj.getPayment().setOrder(obj);
		
		if(obj.getPayment() instanceof PaymentWithBankSlip) {
			PaymentWithBankSlip pay = (PaymentWithBankSlip) obj.getPayment();
			slipBankService.fillPaymentWithSlipBank(pay, obj.getInstant());
		}
		
		obj = orderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		
		for(OrderedItem ordered : obj.getItems()) {
			ordered.setDiscount(0.0);
			ordered.setProduct(productService.findById(ordered.getProduct().getId()));
			ordered.setPrice(ordered.getProduct().getPrice());
			ordered.setOrder(obj);
		}
		orderedItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
