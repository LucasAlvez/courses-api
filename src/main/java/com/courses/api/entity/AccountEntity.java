package com.courses.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity(name = "account")
@Table(name = "account")
public class AccountEntity implements Serializable {
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
	
	@Valid
	@OneToOne(mappedBy = "account")
	private UserEntity user;

	@OneToMany(mappedBy = "account")
	private List<CourseEntity> categories;
	
	@OneToMany(mappedBy = "account")
	private List<CourseEntity> courses;

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

	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<CourseEntity> getCategories() {
		return categories;
	}

	public void setCategories(List<CourseEntity> categories) {
		this.categories = categories;
	}
	
	public List<CourseEntity> getCourses() {
		return courses;
	}
	
	public void setCourses(List<CourseEntity> courses) {
		this.courses = courses;
	}
}
