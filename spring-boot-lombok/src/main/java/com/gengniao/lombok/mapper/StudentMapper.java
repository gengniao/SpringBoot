package com.gengniao.lombok.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gengniao.lombok.pojo.Student;

public interface StudentMapper {

	/**
	 * 查询所有学生信息
	 * @return
	 */
	@Select("select * from T_STUDENT")
	@Results({
		@Result(property="name", column="name", javaType=String.class),
		@Result(property="gender", column="gender")
	})
	List<Student> getAll();
	
	@Select("slelect * from T_STUDENTdent where id = #{id}")
	Student getOne(Integer id);
	
	@Insert("insert into T_STUDENTdent values(#{id},#{name},#{gender})")
	void insert(Student s);
	
	@Update("update T_STUDENTdent set gender = #{gender},name=#{name} where id = #{id}")
	void update(Student s);
	
	@Delete("delete from T_STUDENTdent where id = #{id}")
	void delete(Integer id);
}
