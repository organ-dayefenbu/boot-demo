package com.demo.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.boot.vo.in.TestVo;


@RestController
@RequestMapping("test")
public class TestController extends BaseController{
	Logger logger = LoggerFactory.getLogger(TestController.class);
	@RequestMapping(method=RequestMethod.GET)
	public void test(TestVo vo){
		logger.info("sssssssssssssssssss");
		logger.debug("vvvvvvvvvvvvvvvvvvv");
		logger.error("mmmmmmmmmmmmmmmmmmmm");
		System.out.println("AAAAAAAAAAAAA");
//		return result;
	}
	
	
	

}
