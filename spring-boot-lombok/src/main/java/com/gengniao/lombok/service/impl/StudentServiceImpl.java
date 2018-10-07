package com.gengniao.lombok.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gengniao.lombok.mapper.StudentMapper;
import com.gengniao.lombok.pojo.Student;
import com.gengniao.lombok.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<Student> getAll() {
		return studentMapper.getAll();
	}

	@Override
	public Student getOne(Integer id) {
		return studentMapper.getOne(id);
	}

	@Override
	public void insert(Student s) {
		studentMapper.insert(s);
	}

	@Override
	public void update(Student s) {
		studentMapper.update(s);
	}

	@Override
	public void delete(Integer id) {
		studentMapper.delete(id);
	}

}
