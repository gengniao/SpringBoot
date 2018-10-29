package com.zhiyue.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyue.mybatis.pojo.Student;
import com.zhiyue.mybatis.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	/**
	 * url: localhost:8081/getAll
	 * type : get
	 * @return
	 */
	@RequestMapping("getAll")
	public List<Student> getAll(){
		return studentService.getAll();
	}
	
	@RequestMapping("get")
	public String getOne() {
		return "Hello";
	}
}
