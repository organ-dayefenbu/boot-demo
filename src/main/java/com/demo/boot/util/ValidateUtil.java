package com.demo.boot.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateUtil {
	/**
	 * 参数校验
	 * @param param
	 * @return
	 */
	public static String validate(Object param) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Object>> set = validator.validate(param);
        
        if (set.size() == 0) {
        	return null;
        }
        
        StringBuffer errorMessage = new StringBuffer();
        
        for (ConstraintViolation<Object> c: set) {
        	errorMessage.append(c.getPropertyPath());
        	errorMessage.append("校验失败,");
        	errorMessage.append(c.getMessage());
        	errorMessage.append(".输入为: ");
        	errorMessage.append(c.getInvalidValue());
        	
        	errorMessage.append("\n");
        }
        
        return  errorMessage.toString();
	}
}
