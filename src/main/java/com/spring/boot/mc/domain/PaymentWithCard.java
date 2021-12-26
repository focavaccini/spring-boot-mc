package com.spring.boot.mc.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.spring.boot.mc.domain.enums.PaymentState;

@Entity
@Table(name = "tb_payment_with_card")
public class PaymentWithCard extends Payment{

	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;

	public PaymentWithCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentWithCard(Integer id, PaymentState paymentState, Order order, Integer numberOfInstallments) {
		super(id, paymentState, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	
}
