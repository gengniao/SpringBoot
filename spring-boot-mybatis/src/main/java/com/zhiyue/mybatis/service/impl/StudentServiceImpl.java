package com.zhiyue.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyue.mybatis.mapper.StudentMapper;
import com.zhiyue.mybatis.pojo.Student;
import com.zhiyue.mybatis.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentMapper studentMapper;
	
	public List<Student> getAll() {
		System.out.println("come in the student service!");
		return studentMapper.getAll();
	}

	public Student getOne(Long stuid) {
		return studentMapper.getOne(stuid);
	}

	public void insert(Student student) {
		studentMapper.insert(student);
	}

	public void update(Student student) {
		studentMapper.update(student);
		
	}

	public void delete(Long stuid) {
		studentMapper.delete(stuid);
	}
	

}
