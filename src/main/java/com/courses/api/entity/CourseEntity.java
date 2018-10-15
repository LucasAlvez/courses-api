package com.courses.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity(name = "courses")
@Table(name = "courses")
public class CourseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Integer duration;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createDate;

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime updateDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private AccountEntity account;

	@ManyToMany
	@JoinTable(name = "courses_categories", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	private List<CategoryEntity> categories;
	
	@ManyToMany(mappedBy = "courses")
	private List<StudentEntity> students;
	
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

	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<CategoryEntity> getCategories() {
		return categories;
	}
	
	public void setCategories(List<CategoryEntity> categories) {
		this.categories = categories;
	}
	
	public List<StudentEntity> getStudents() {
		return students;
	}
	
	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	public AccountEntity getAccount() {
		return account;
	}
	
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
}
