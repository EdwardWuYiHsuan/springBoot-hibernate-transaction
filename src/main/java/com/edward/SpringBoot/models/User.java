package com.edward.SpringBoot.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import com.edward.SpringBoot.util.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	@JsonIgnore  // Spring Framework's responsebody use Jackson library to response POJO json data.
	private String password;
	private String phone;
	@NotNull
	@Type(type = "timestamp")
	private Date creationDate;
	
	
	public User() {
		this.creationDate = new Date();
	}
	
	public Long getId() {
		return id;
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
		if (!Utils.validateEmail(email))
			throw new IllegalArgumentException("invalid-email");
			
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (StringUtils.isBlank(password.trim()))
			throw new IllegalArgumentException("invalid-password");
		
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	
}
