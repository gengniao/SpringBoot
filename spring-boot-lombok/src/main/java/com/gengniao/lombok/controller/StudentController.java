package com.gengniao.lombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gengniao.lombok.pojo.Student;
import com.gengniao.lombok.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("getAll")
	public List<Student> getAll(){
		log.info("============================================");
		log.info("查询所有学生信息服务已经就绪");
		return studentService.getAll();
	}
	
	@RequestMapping("hello")
	public String hello() {
		log.info("============================================");
		log.info("欢迎进入学生信息控制器");
		return "hello";
	}
}
