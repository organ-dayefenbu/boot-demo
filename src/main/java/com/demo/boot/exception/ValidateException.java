package com.demo.boot.exception;

public class ValidateException extends CustomException{
	private static final long validateCode = 50000L;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5394821462355364335L;
	/**
	 * 
	 * @param msg 校验错误信息
	 */
	public ValidateException(String msg){
		super(validateCode,msg);
	}

}
