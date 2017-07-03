package com.edward.SpringBoot.service.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.edward.SpringBoot.dao.UserDao;
import com.edward.SpringBoot.exception.APICode;
import com.edward.SpringBoot.exception.ApiException;
import com.edward.SpringBoot.models.User;
import com.edward.SpringBoot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDao userDao;
	
	
	@Override
	public Iterable<User> getUserList()
	{
		return userDao.findAll();
	}
	
	@Override
	public User getUser(Long userId) throws ApiException
	{
		if (!userDao.exists(userId)) {
			throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
		}
		
		return userDao.findOne(userId);
	}
	
	@Override
	public List<User> getUserByName(String name)
	{
		return userDao.findUserByName(name);
	}
	
	@Override
	public void createUser(User user) throws ApiException
	{
		try {
			userDao.save(user);
		} catch (DataIntegrityViolationException e) {	// Belong to Spring Framework's Exception，Spring wrap hibernate's exception which execution sql cause fail.
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new ApiException(APICode.recordUniqueValue, "record-unique-value");
			} else {
				throw new ApiException(APICode.Other, e.getMessage());
			}
		}
	}
	
	@Override
	public void updateUser(Long userId, User updateUser) throws ApiException
	{
		try {
			User user = this.getUser(userId);
			if (null == user)
				throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
			
			if (StringUtils.isNotBlank(updateUser.getName())) {
				user.setName(updateUser.getName());
			}
			if (StringUtils.isNotBlank(updateUser.getEmail())) {
				user.setEmail(updateUser.getEmail());
			}
			if (StringUtils.isNotBlank(updateUser.getPassword())) {
				user.setPassword(updateUser.getPassword());
			}
			if (StringUtils.isNotBlank(updateUser.getPhone())) {
				user.setPhone(updateUser.getPhone());
			}
			
			userDao.save(user);
			
		} catch (DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new ApiException(APICode.recordUniqueValue, "record-unique-value");
			} else {
				throw new ApiException(APICode.Other, e.getMessage());
			}
		}
	}
	
	@Override
	public void deleteUser(Long userId) throws ApiException
	{
		if (!userDao.exists(userId)) {
			throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
		}
		
		userDao.delete(userId);
	}
	
}
