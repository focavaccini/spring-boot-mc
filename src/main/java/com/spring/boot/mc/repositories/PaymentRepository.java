package com.spring.boot.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.mc.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
