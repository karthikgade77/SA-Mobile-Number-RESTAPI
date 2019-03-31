package com.service;

import org.springframework.stereotype.Component;

@Component("phoneNumberValidator")
public class PhoneNumberValidator implements Validator{
	 
	@Override
	public boolean validate(String number)
	{
		String pattern = "((60[3-9]|64[0-5]|66[0-5])\\d{6}|(7[1-4689]|6[1-3]|8[1-4])\\d{7})";
		return number.matches(pattern);
	}
	
	@Override
	public boolean isNonNumeric(String number)
	{
		String nonNumericPattern = "([^\\d])";
		return number.matches(nonNumericPattern);
	}

}
