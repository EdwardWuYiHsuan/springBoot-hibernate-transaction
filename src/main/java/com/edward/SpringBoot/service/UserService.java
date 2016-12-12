package com.edward.SpringBoot.service;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edward.SpringBoot.dao.UserDao;
import com.edward.SpringBoot.exception.APICode;
import com.edward.SpringBoot.exception.ApiException;
import com.edward.SpringBoot.models.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	
	public void createUser(User user) throws ApiException
	{
		try {
			userDao.save(user);
		} catch (ConstraintViolationException e) {
			throw new ApiException(APICode.recordUniqueValue, "record-unique-value");
		}
	}
	
	public void updateUser(User originUser, String name, String email, String password, String phone) throws ApiException
	{
		try {
			if (StringUtils.isNotBlank(name))
				originUser.setName(name);
			if (StringUtils.isNotBlank(email))
				originUser.setEmail(email);
			if (StringUtils.isNotBlank(password))
				originUser.setPassword(password);
			if (StringUtils.isNotBlank(phone))
				originUser.setPhone(phone);
			
			userDao.save(originUser);
			
		} catch (ConstraintViolationException e) {
			throw new ApiException(APICode.recordUniqueValue, "record-unique-value");
		} catch (Exception e) {
			throw new ApiException(APICode.Other, e);
		}
	}
	
	public void deleteUser(Long userId) throws ApiException
	{
		if (!userDao.exists(userId))
			throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
		
		userDao.delete(userId);
	}
	
	public Iterable<User> getUserList()
	{
		return userDao.findAll();
	}
	
	public User getUser(Long userId) throws ApiException
	{
		if (!userDao.exists(userId))
			throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
		
		return userDao.findOne(userId);
	}
	
	
}
