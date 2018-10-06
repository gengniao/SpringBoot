# Spring-boot-mybatis-annotation 的 BindingException

## 异常详情
```debug
org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.zhiyue.mybatis.service.StudentService.getAll
	at org.apache.ibatis.binding.MapperMethod$SqlCommand.<init>(MapperMethod.java:214) ~[mybatis-3.4.0.jar:3.4.0]
	at org.apache.ibatis.binding.MapperMethod.<init>(MapperMethod.java:48) ~[mybatis-3.4.0.jar:3.4.0]
	at org.apache.ibatis.binding.MapperProxy.cachedMapperMethod(MapperProxy.java:59) ~[mybatis-3.4.0.jar:3.4.0]
	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:52) ~[mybatis-3.4.0.jar:3.4.0]
	at com.sun.proxy.$Proxy85.getAll(Unknown Source) ~[na:na]
```

## 异常原因
![](https://i.imgur.com/0TkYGmF.png)
```java
@SpringBootApplication
@MapperScan("com.zhiyue.mybatis")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
## 解决方式
+ 1. 在Application.class中修改包扫描为mpper.class的直接包 @MapperScan("com.zhiyue.mybatis.<strong style="color:red;">mapper</strong>")
```java
@SpringBootApplication
@MapperScan("com.zhiyue.mybatis.mapper")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
+ 2. 取消Application.class中的**@MapperScan**,改在`mapper`包中的StudentMapper.java中使用**@Mapper**注解
```java
@Mapper
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
``` 