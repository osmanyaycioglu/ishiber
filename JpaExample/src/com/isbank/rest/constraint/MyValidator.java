package com.isbank.rest.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator<MyAnnoValid, String>{
	private int length;
	
	@Override
	public void initialize(MyAnnoValid constraintAnnotation) {
		length = constraintAnnotation.length();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() <= length;
	}

}
