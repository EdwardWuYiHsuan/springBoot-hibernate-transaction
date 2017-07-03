package com.edward.SpringBoot.service;


import java.util.List;

import com.edward.SpringBoot.exception.ApiException;
import com.edward.SpringBoot.models.User;


public interface UserService {
	
	/**
	 * Get all users as a list
	 * @return	User list		iterable of users
	 * @throws 	ApiException	business logic issue
	 */
	public Iterable<User> getUserList() throws ApiException;
	
	/**
	 * Get user by userId
	 * @param 	userId			user's ID
	 * @return	User			{@link User}
	 * @throws 	ApiException	business logic issue
	 */
	public User getUser(Long userId) throws ApiException;
	
	/**
	 * Get user by name
	 * @param 	name			user name
	 * @return	User list		user list
	 * @throws 	ApiException	business logic issue
	 */
	public List<User> getUserByName(String name) throws ApiException;
	
	/**
	 * Create user
	 * @param 	user			{@link User}
	 * @throws 	ApiException	business logic issue
	 */
	public void createUser(User user) throws ApiException;
	
	/**
	 * Update User
	 * @param 	userId			user's ID
	 * @param 	updateUser		updated user
	 * @throws 	ApiException	business logic issue
	 */
	public void updateUser(Long userId, User updateUser) throws ApiException;
	
	/**
	 * Delete user
	 * @param 	userId			user's ID
	 * @throws 	ApiException	business logic issue
	 */
	public void deleteUser(Long userId) throws ApiException;
	
}
