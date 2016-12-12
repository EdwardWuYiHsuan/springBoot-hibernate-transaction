package com.edward.SpringBoot.exception;

public enum APICode {
	
	OK(0),
	InvalidParameter(101),
	recordUniqueValue(102),
	Other(999);
	
	
	private int code;
	APICode(int code) 
	{
		this.code = code;
	}
	
	public int getCode()
	{
		return code;
	}

}
