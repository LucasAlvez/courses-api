package com.courses.api.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.courses.api.enums.Roles;

@Entity(name = "students")
@Table(name = "students")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String pass;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ROLES")
	private Set<Integer> roles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<Roles> getRoles() {
		return roles.stream().map(x -> Roles.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addRoles (Roles role) {	
		roles.add(role.getCod());
	}
}
