package com.edward.SpringBoot.dao;

import org.springframework.data.repository.CrudRepository;

import com.edward.SpringBoot.models.User;

public interface UserDao extends CrudRepository<User, Long> {

	
}
