package com.gengniao.lombok.pojo;

import lombok.Data;
/**
 * lombok 的@Date注解,提供setter,getter,无参构造,equal,hashCode方法
 * @Description 
 * @ClassName com.gengniao.lombok.pojo.User
 * @author 孔子不悦
 * @Date 2018年10月6日
 */
@Data
public class Student {
	
	private Integer id;
	private String name;
	private String gender;
}
