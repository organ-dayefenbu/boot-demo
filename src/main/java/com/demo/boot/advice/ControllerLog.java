package com.demo.boot.advice;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.demo.boot.util.ValidateUtil;

@Component
@Aspect
public class ControllerLog {
	Logger logger = LoggerFactory.getLogger(ControllerLog.class);
	
	
	/**
	 * 日志记录controller,并校验请求参数
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around(value="execution(public * com.demo.boot.controller.*.*(..))")
	public Object providerProcess(ProceedingJoinPoint point) throws Throwable{
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        long start = System.currentTimeMillis();
		Object[] params = point.getArgs();
		if(null != params && params.length>0){
			for(Object o : params){
				String validate = ValidateUtil.validate(o);
				if(!StringUtils.isEmpty(validate)){
					System.out.println(validate);
				}
			}
		}
		logger.info("["+url+"===={}]请求参数:{}", method, JSONObject.toJSONString(params));
		Object returnValue = point.proceed(params);
		long end = System.currentTimeMillis();
		logger.info("["+url+"===={}]耗时"+(end-start)+"毫秒****返回参数:{}", method, JSONObject.toJSONString(returnValue));
		return returnValue;
	}

}
