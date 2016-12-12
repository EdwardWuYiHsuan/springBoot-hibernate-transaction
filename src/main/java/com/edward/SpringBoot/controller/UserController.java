package com.edward.SpringBoot.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edward.SpringBoot.dao.UserDao;
import com.edward.SpringBoot.exception.APICode;
import com.edward.SpringBoot.exception.ApiException;
import com.edward.SpringBoot.models.User;
import com.edward.SpringBoot.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public Map<String, Object> createUser(@RequestParam(value = "name") String name,
										  @RequestParam(value = "email", required = true) String email,
										  @RequestParam(value = "password", required = true) String pwd,
										  @RequestParam(value = "phone") String phone)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (StringUtils.isBlank(email))
				throw new ApiException(APICode.InvalidParameter, "invalid-email");
			if (StringUtils.isBlank(pwd))
				throw new ApiException(APICode.InvalidParameter, "invalid-password");
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(pwd);
			user.setPhone(phone);
			
			userService.createUser(user);
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public Map<String , Object> updateUser(@RequestParam(value = "userId") Long userId,
										   @RequestParam(value = "name") String name,
										   @RequestParam(value = "email") String email,
										   @RequestParam(value = "password") String password,
										   @RequestParam(value = "phone") String phone)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			User user = userService.getUser(userId);
			userService.updateUser(user, name, email, password, phone);
			
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public Map<String, Object> deleteUser(@RequestParam(value = "userId", required = true) Long userId)
	{
		Map<String, Object> responseRes = new HashMap<>();
		try {
			if (null == userId)
				throw new ApiException(APICode.InvalidParameter, "invalid-user-id");
			
			userService.deleteUser(userId);
			
			responseRes.put("result", "ok");
			
		} catch (Exception e) {
			responseRes.put("result", "error");
			responseRes.put("reason", e.getMessage());
		}
		
		return responseRes;
	}
	
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
	public Map<String, Object> getUser(@RequestParam(value = "userId", required = true) Long userId)
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
	
	
}
