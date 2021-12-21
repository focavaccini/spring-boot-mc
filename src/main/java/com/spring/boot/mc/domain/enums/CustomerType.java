package com.spring.boot.mc.domain.enums;

public enum CustomerType {
	PHYSICAL_PERSON(1),
	LEGAL_PERSON(2);
	
	private int code;
	
	private CustomerType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static CustomerType toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(CustomerType x : CustomerType.values()) {
			if(code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id " + code);
	}
}
