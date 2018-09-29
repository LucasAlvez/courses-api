package com.courses.api.enums;

public enum Roles {
	USER(1, "ROLE_USER"), ADMIN(2, "ROLE_ADMIN");

	private int cod;
	private String roles;
	
	private Roles(int cod, String roles) {
		this.cod = cod;
		this.roles = roles;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public static Roles toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Roles x : Roles.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}	
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
