package com.edward.SpringBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.edward.SpringBoot.models.User;

public interface UserDao extends CrudRepository<User, Long> {

	
	@Query("from User u where u.name=:name")
	public List<User> findUserByName(@Param("name") String name);
	
	
}