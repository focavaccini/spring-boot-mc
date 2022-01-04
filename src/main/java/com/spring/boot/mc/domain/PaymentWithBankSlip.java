package com.spring.boot.mc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spring.boot.mc.domain.enums.PaymentState;

@Entity
@Table(name = "tb_payment_with_bank_slip")
@JsonTypeName("paymentWithBankSlip")
public class PaymentWithBankSlip extends Payment{
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date payday;
	public PaymentWithBankSlip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentWithBankSlip(Integer id, PaymentState paymentState, Order order,  Date dueDate, Date payday) {
		super(id, paymentState, order);
		this.dueDate = dueDate;
		this.payday = payday;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getPayday() {
		return payday;
	}
	public void setPayday(Date payday) {
		this.payday = payday;
	}
	
	
}
