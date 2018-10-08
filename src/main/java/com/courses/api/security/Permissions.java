package com.courses.api.security;

public class Permissions {

	public static final String DEFAULT = "hasAnyRole('USER')";
	
	public static final String ADMIN = "hasAnyRole('ADMIN')";
 
}