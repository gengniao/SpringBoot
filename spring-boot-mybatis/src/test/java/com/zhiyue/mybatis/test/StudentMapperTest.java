package com.zhiyue.mybatis.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zhiyue.mybatis.mapper.StudentMapper;
import com.zhiyue.mybatis.pojo.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
 
	@Autowired
	private StudentMapper studentMapper;
 
	@Test
	public void testInsert() throws Exception {
		studentMapper.insert(new Student(7L,"Synchronization", "男"));
		studentMapper.insert(new Student(8L,"well", "女"));
		studentMapper.insert(new Student(9L,"spicy", "男"));
	}
 
	@Test
	public void testQuery() throws Exception {
		List<Student> users = studentMapper.getAll();
		System.out.println(users.toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Student user = studentMapper.getOne(4L);
		user.setStuname("庄子");
		studentMapper.update(user);
	}
}
