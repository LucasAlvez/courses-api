package com.courses.api.request;

import com.courses.api.entity.RoleEntity;

public class UserRolesRequest {
	private RoleEntity role;

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
}
