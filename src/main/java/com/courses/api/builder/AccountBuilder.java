package com.courses.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.AccountEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.request.AccountRequest;
import com.courses.api.request.AddressRequest;
import com.courses.api.request.ContactRequest;
import com.courses.api.response.AccountResponse;
import com.courses.api.response.AccountUpdateResponse;
import com.courses.api.response.AddressResponse;
import com.courses.api.response.ContactResponse;

public class AccountBuilder {

	public static AccountEntity buildUpdateRequest(AccountRequest request, AccountEntity entity, UserEntity user) {
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPass(request.getPass());
		entity.setAddress(AddressRequest.build(request.getAddress()));
		entity.setContact(ContactRequest.build(request.getContact()));
		entity.setUpdateDate(LocalDateTime.now());

		// User
		user.setName(request.getEmail());
		user.setEmail(request.getEmail());
		user.setPass(request.getPass());
		user.setUpdateDate(LocalDateTime.now());
		return entity;
	}

	public static AccountResponse buildResponse(AccountEntity entity) {
		AccountResponse response = new AccountResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		return response;
	}

	public static AccountUpdateResponse buildResponseDetails(AccountEntity entity) {
		AccountUpdateResponse response = new AccountUpdateResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setAddress(AddressResponse.build(entity.getAddress()));
		response.setContact(ContactResponse.build(entity.getContact()));
		response.setCreateDate(entity.getCreateDate().toString());
		response.setUpdateDate(entity.getUpdateDate().toString());
		return response;
	}

	public static List<AccountResponse> to(List<AccountEntity> entity) {
		List<AccountResponse> list = new ArrayList<>();
		if (entity.isEmpty() || entity == null)
			return list;

		entity.forEach(account -> {
			list.add(buildResponse(account));
		});
		return list;
	}

}
