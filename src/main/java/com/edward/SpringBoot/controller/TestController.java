package com.edward.SpringBoot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping("/")	// http://localhost:8080/
	public String getString() 
	{
		return "Hello World!! Spring Boot";
	}
	
	@RequestMapping("/{value}")		//http://localhost:8080/hello_world
	public String printPathVariable(@PathVariable("value") String value)
	{
		return "Path Variable : " + value;
	}
	
	@RequestMapping("printRequestParameterByGet")	//http://localhost:8080/printRequestParameterByGet?params=aa
	public String printRequestParameterByGet(@RequestParam(name = "params") String params)
	{
		return "GET Request Parameter : " + params;
	}
	
	@RequestMapping(path = "/printRequestParameterByPost", method = RequestMethod.POST)
	public String printRequestParameterByPost(@RequestParam(name = "params") String params)
	{
		return "POST Request Parameter : " + params;
	}
}


