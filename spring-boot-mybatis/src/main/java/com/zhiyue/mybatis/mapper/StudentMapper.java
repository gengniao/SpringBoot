package com.zhiyue.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhiyue.mybatis.pojo.Student;
public interface StudentMapper {
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	@Select("SELECT * FROM T_STU")
	@Results({
		@Result(property = "stuname", column = "stuname", javaType = String.class),
		@Result(property = "stusex", column = "stusex")
	})
	List<Student> getAll();
	
	@Select("select * from T_STU where stuid = #{stuid}")
	@Results({
		@Result(property = "stusex",column = "stusex"),
		@Result(property = "stuname",column = "stuname")
	})
	Student getOne(Long stuid);
	
	@Insert("INSERT INTO T_STU(stuid,stuname,stusex) VALUES(#{stuid},#{stuname}, #{stusex})")	
	void insert(Student student); 	
	
	@Update("UPDATE T_STU SET stusex=#{stusex},stuname=#{stuname} WHERE stuid =#{stuid}")	
	void update(Student student); 
	
	@Delete("DELETE FROM T_STU WHERE id =#{stuid}")	
	void delete(Long stuid);

	
}
