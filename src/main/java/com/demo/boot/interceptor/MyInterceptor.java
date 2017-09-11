package com.demo.boot.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * web拦截器，添加前置校验
 * @author lism
 *
 */
public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());
		modifyThreadName();
		return true;
	}
	/**
	 * 重命名线程名，方便高并发下的日志查找
	 */
	private void modifyThreadName(){
		Thread t = Thread.currentThread();
		t.setName("Thread"+UUID.randomUUID().toString().replace("-", ""));
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
