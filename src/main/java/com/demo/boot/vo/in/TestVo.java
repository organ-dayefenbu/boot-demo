package com.demo.boot.vo.in;

import org.hibernate.validator.constraints.NotBlank;

public class TestVo {
	@NotBlank(message="name不能为空")
	private String name;
	@NotBlank(message="age不能为空")
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	

}
