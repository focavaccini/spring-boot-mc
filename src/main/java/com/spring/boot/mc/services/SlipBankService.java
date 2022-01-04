package com.spring.boot.mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.spring.boot.mc.domain.PaymentWithBankSlip;

@Service
public class SlipBankService {

	public void fillPaymentWithSlipBank(PaymentWithBankSlip payment, Date orderTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		
		payment.setDueDate(orderTime);
	}
}
