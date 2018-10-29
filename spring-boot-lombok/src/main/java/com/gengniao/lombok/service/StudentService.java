package com.gengniao.lombok.service;

import java.util.List;

import com.gengniao.lombok.pojo.Student;

public interface StudentService {
	
	List<Student> getAll();
	
	Student getOne(Integer id);
	
	void insert(Student s);
	
	void update(Student s);
	
	void delete(Integer id);
}
