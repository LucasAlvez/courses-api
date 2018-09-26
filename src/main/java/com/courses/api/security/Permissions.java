package com.courses.api.security;

public class Permissions {

	/**
	 * Role default utilizada para acesso dos usuários a API com permissões básicas de acesso.
	 */
	public static final String DEFAULT = "hasAuthority('user')";
	
	/**
	 * Role para serviços que necessitam permissão de administrador.
	 */
	public static final String ADMIN = "hasAuthority('admin')";
 
}