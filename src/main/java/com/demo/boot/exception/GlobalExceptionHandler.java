package com.demo.boot.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class GlobalExceptionHandler{
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object jsonErrorHandler(HttpServletRequest req, Exception ex){
        logger.error(ex.toString(),ex);
        
        long code = 10000;
		String errMessage = "系统繁忙,请稍后再试！";
		if(ex.getClass().equals(CustomException.class)){
			code = ((CustomException) ex).getCode();
			errMessage = ex.getMessage();
		} else if(ex.getClass().equals(ValidateException.class)){
			code = ((ValidateException) ex).getCode();
			errMessage = ex.getMessage();
		}else if (ex.getClass().equals(NullPointerException.class)) {
			errMessage = "调用了未经初始化的对象或者是不存在的对象！";
		} else if (ex.getClass().equals(IOException.class)) {
			errMessage = "IO异常！";
		} else if (ex.getClass().equals(ClassNotFoundException.class)) {
			errMessage = "指定的类不存在！";
		} else if (ex.getClass().equals(ArithmeticException.class)) {
			errMessage = "数学运算异常！";
		} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
			errMessage = "数组下标越界!";
		} else if (ex.getClass().equals(IllegalArgumentException.class)) {
			errMessage = "方法的参数错误！";
		} else if (ex.getClass().equals(ClassCastException.class)) {
			errMessage = "类型强制转换错误！";
		} else if (ex.getClass().equals(SecurityException.class)) {
			errMessage = "违背安全原则异常！";
		} else if (ex.getClass().equals(SQLException.class)) {
			errMessage = "操作数据库异常！";
		} else if (ex.getClass().equals(NoSuchMethodError.class)) {
			errMessage = "方法末找到异常！";
		} else if (ex.getClass().equals(InternalError.class)) {
			errMessage = "Java虚拟机发生了内部错误";
		}else {
			errMessage = "程序内部错误，操作失败！";
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("msg", errMessage);
		map.put("status", code);
        
        return map;
    }
	
	

}
