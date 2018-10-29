# Spring-boot 日志入门配置

## 搭建 SpringBoot 项目
- **pom.xml**
```xml
    <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.6.RELEASE</version>
	</parent>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>			
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
	
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
```
## 配置 application.properties
- **application.properties**
```properties
server.port=8081
# 配置context-path
server.context-path=/demo
# 配置 日志
logging.file=d:/logs/demo.log
debug=true
logging.level.com.sundyn.task=info
logging.level.com.sundyn.controller=info
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss}[%thread] &-5level%logger-%msg%n
# gaox= I am happy !，可以在项目中能过@value("${gaox}")取到
gaox= I am happy!
```
## 配置SpringBoot启动类
- **Application.java**
```java
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
## 配置控制器
- **LogController.java**
```java
@RestController
public class LogController {
	
	@RequestMapping("hello")
	public String hello() {
		return "hello world!";
	}
	
	@Value("${gaox}")
	private String gaox;
	
	private Logger log = Logger.getLogger(LogController.class);
	
	@RequestMapping("log")
	public String log() {
		return gaox;
	}
}
```

## 注意事项
> `application.properties`中的日志输出的位置在真实服务器中需要使用相对路径
