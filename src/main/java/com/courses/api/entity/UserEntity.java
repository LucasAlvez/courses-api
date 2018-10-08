package com.courses.api.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
@Table(name = "users")
public class UserEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String pass;
	
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createDate;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime updateDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	@JoinColumn(name = "role_id") })
	private Set<RoleEntity> roles = new HashSet<>();

	@Valid
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private StudentEntity student;

	public UserEntity() {
	}

	public UserEntity(Long id, String name, String email, String pass, Set<RoleEntity> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		return this.pass;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}