package com.courses.api.response;

public class StudentResponse {

	private Long id;

	private AccountResponse account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountResponse getAccount() {
		return account;
	}

	public void setAccount(AccountResponse account) {
		this.account = account;
	}
}
