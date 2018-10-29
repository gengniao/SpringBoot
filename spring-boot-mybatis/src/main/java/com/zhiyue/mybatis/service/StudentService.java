package com.zhiyue.mybatis.service;

import java.util.List;

import com.zhiyue.mybatis.pojo.Student;

public interface StudentService {
	

	List<Student> getAll();
	
	Student getOne(Long stuid);
	
	void insert(Student student); 	
	
	void update(Student student); 
	
	void delete(Long stuid);	
}
