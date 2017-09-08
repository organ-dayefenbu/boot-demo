package com.demo.boot.controller;

import javax.annotation.Resource;

import com.demo.boot.dao.AaaDAO;

public class BaseController {
	@Resource
	AaaDAO aaaDao;

}
