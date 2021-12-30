package com.spring.boot.mc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.boot.mc.domain.enums.CustomerType;
import com.spring.boot.mc.dto.ClientNewDTO;
import com.spring.boot.mc.resources.exception.FieldMessage;
import com.spring.boot.mc.services.validation.utils.BR;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientNewDTO> {
	@Override
	public void initialize(InsertClient ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getType().equals(CustomerType.PHYSICAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CPF"));
		}
		
		if(objDto.getType().equals(CustomerType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "Invalid CNPJ"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}