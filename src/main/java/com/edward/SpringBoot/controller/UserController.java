package com.edward.SpringBoot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edward.SpringBoot.exception.APICode;
import com.edward.SpringBoot.exception.ApiException;
import com.edward.SpringBoot.models.User;
import com.edward.SpringBoot.service.UserService;
import com.edward.SpringBoot.util.Utils;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "getAllUsers", method = RequestMethod.GET)
	public Map<String, Object> getAllUsers()
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			Iterable<User> userList = userService.getUserList();
			
			responseRes.put("result", "ok");
			responseRes.put("data", userList);
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public Map<String, Object> getUser(@RequestParam(value = "userId") Long userId)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (null == userId)
				throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
			
			User user = userService.getUser(userId);
			
			responseRes.put("result", "ok");
			responseRes.put("data", user);
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "getUserByName", method = RequestMethod.GET)
	public Map<String, Object> getUserByName(@RequestParam(value = "name") String name)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (null == name)
				throw new ApiException(APICode.InvalidParameter, "invalid-name");
			
			List<User> users = userService.getUserByName(name);
			
			responseRes.put("result", "ok");
			responseRes.put("data", users);
		}
		catch (Exception e) 
		{
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public Map<String, Object> createUser(@RequestParam(value = "name", required = false) String name,
										  @RequestParam(value = "email") String email,
										  @RequestParam(value = "password") String password,
										  @RequestParam(value = "phone", required = false) String phone)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (!Utils.validateEmail(email))
				throw new ApiException(APICode.InvalidParameter, "invalid-email");
			if (StringUtils.isBlank(password))
				throw new ApiException(APICode.InvalidParameter, "invalid-password");
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			
			userService.createUser(user);
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "updateUser/{userId}", method = RequestMethod.PUT)
	public Map<String , Object> updateUser(@PathVariable Long userId, @RequestBody User updateUser)  //RESTful-put method.
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (null == userId)
				throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
			
			userService.updateUser(userId, updateUser);
			
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "deleteUser/{userId}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(@PathVariable Long userId)  //RESTful-delete method.
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			userService.deleteUser(userId);
			
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	
}
