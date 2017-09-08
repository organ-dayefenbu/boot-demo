package com.demo.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.boot.vo.in.TestVo;


@RestController
@RequestMapping("test")
public class TestController extends BaseController{
	@RequestMapping(method=RequestMethod.GET)
	public void test(TestVo vo){
		System.out.println("AAAAAAAAAAAAA");
//		return result;
	}
	
	
	

}
