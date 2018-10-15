package com.courses.api.builder;

import java.util.ArrayList;
import java.util.List;

import com.courses.api.entity.AccountEntity;
import com.courses.api.response.AccountResponse;

public class AccountBuilder {
	

	public static AccountResponse buildResponse(AccountEntity entity) {
		AccountResponse response = new AccountResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
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
