# spring-boot-lombok 入门测试

## 1.spring-boot 集成lombok

- pom.xml

```pom
<parent>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-parent</artifactId>
  	<version>1.5.6.RELEASE</version>
  </parent>
  
  <dependencies>
  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-web</artifactId>
  		<!-- SpringBoot 项目打 war 包需要排除内嵌的tomcat -->
<!--   		<exclusions>
  			<exclusion>
  				<groupId>org.springframework.boot</groupId>
  				<artifactId>spring-boot-starter-tomcat</artifactId>
  			</exclusion>
  		</exclusions> -->
  	</dependency>
  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-test</artifactId>
  		<scope>test</scope>
  	</dependency>
  	<!-- SpringBoot 整合lombok -->
  	<dependency>
  		<groupId>org.projectlombok</groupId>
  		<artifactId>lombok</artifactId>
  	</dependency>
	<!-- SpringBoot项目打war包需要 -->
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>javax.servlet-api</artifactId>
<!--   		<scope>provided</scope> -->
  	</dependency>
  	<!-- mybatis 对接spingboot -->
  	<dependency>
  		<groupId>org.mybatis.spring.boot</groupId>
  		<artifactId>mybatis-spring-boot-starter</artifactId>
  		<version>1.3.0</version>
  	</dependency>
  	<!-- oracle -->
  	<dependency>
  		<groupId>com.oracle</groupId>
  		<artifactId>ojdbc6</artifactId>
  		<version>11.2.0.1</version>
  	</dependency>
  	<!-- 热部署 -->
  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-devtools</artifactId>
  	</dependency>
  </dependencies>
  
  <build>
    <!-- 使用外部Tomcat部署访问时, server.port和server.servlet.context-path将失效,端口号为外置tomcat的端口号,上下文为<finalName>中的配置 -->
    <finalName>/demo</finalName>
  	<plugins>
  		<plugin>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-maven-plugin</artifactId>
  		</plugin>
  		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
			</configuration>
		</plugin>
  	</plugins>
  </build>
```

## 2.配置启动类Application.java
- Application.java

```java
@SpringBootApplication
@MapperScan("com.gengniao.lombok.mapper")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

## 3.配置文件
- application.yml

```yml
# tomcat 端口号
server:
    port: 8080
    context-path: /lombok
# 日志
logging:
    config: classpath:conf/lombok-dev.xml
    
mybatis:
    type-aliases-package: com.gengniao.mybatis
    mapper-locations: classpath:mappings/*.xml

spring:
    datasource:
        driverClassName: oracle.jdbc.driver.OracleDriver
        url: jdbc:oracle:thin:@localhost:1521:orcl
        username: kongziCrm
        password: kongziCrm
```

- conf/lombok-dev.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 不分级别同步文件日志输出配置 -->
<configuration>
    <!-- 日志级别 -->
    <property name="logLevel" value="INFO"></property>
    <!-- 日志地址 -->
    <property name="logPath" value="./mmmmmmmm"></property>
    <!-- 最大保存时间 -->
    <property name="maxHistory" value="10"/>
    <!-- 异步缓冲队列的深度,该值会影响性能.默认值为256 -->
    <property name="queueSize" value="512"></property>

    <!-- 控制台打印日志的相关配置 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- 日志格式 -->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-4relative [%thread] %-5level %logger{35} - %msg %n</pattern>
        </encoder>
    </appender>

    <!-- 文件保存日志的相关配置，同步 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 保存日志文件的路径 -->
        <file>${logPath}/cms.log</file>
        <!-- 日志格式 -->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-4relative [%thread] %-5level %logger{35} - %msg %n</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>${logLevel}</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <!-- 循环政策：基于时间创建日志文件 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 日志文件名格式 -->
            <fileNamePattern>${logPath}/cms-%d{yyyy-MM-dd}.log</fileNamePattern>
            <!-- 最大保存时间-->
            <maxHistory>${maxHistory}</maxHistory>
        </rollingPolicy>
    </appender>


    <!--配置mybatis sql 日志-->
    <logger name="com.pf.org.cms.mapper" level="DEBUG"/>
    <!-- 基于INFO处理日志：具体控制台或者文件对日志级别的处理还要看所在appender配置的filter，如果没有配置filter，则使用root配置 -->
    <root level="${logLevel}">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```
> [<font color="red">注意</font>]: 输出的日志文件的路径`value="./mmmmmmmm"`为项目根目录下的`mmmmmmmm`

## 4.业务代码
- pojo

```java
@Data
public class Student {
	
	private Integer id;
	private String name;
	private String gender;
}
```

- mapper

```java
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
```

- service

```java
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
```

- controller

```java
@Slf4j
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("getAll")
	public List<Student> getAll(){
		log.info("============================================");
		log.info("查询所有学生信息服务已经就绪");
		return studentService.getAll();
	}
	
	@RequestMapping("hello")
	public String hello() {
		log.info("============================================");
		log.info("欢迎进入学生信息控制器");
		return "hello";
	}
}
```

## 5.本地测试结果
- console

```console
2018-10-06 23:13:34 22577 [http-nio-8080-exec-1] INFO  o.a.c.c.C.[.[localhost].[/lombok] - Initializing Spring FrameworkServlet 'dispatcherServlet' 
2018-10-06 23:13:34 22577 [http-nio-8080-exec-1] INFO  o.s.web.servlet.DispatcherServlet - FrameworkServlet 'dispatcherServlet': initialization started 
2018-10-06 23:13:34 22838 [http-nio-8080-exec-1] INFO  o.s.web.servlet.DispatcherServlet - FrameworkServlet 'dispatcherServlet': initialization completed in 260 ms 
2018-10-06 23:13:34 23010 [http-nio-8080-exec-1] INFO  c.g.l.controller.StudentController - ============================================ 
2018-10-06 23:13:34 23011 [http-nio-8080-exec-1] INFO  c.g.l.controller.StudentController - 查询所有学生信息服务已经就绪 
```
- fileSystem

![](https://i.imgur.com/3OkEk9q.png)
![](https://i.imgur.com/lZlt7Zx.png)

## 6.spring-boot 的 Maven项目打包

- 1.修改pom.xml将打包方式改为<font color="red">**war**</font>
- 2.排除spring-boot-starter-web中的内嵌tomcat

```pom
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

- 3.添加servlet-api依赖

```pom
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```

- 4.由于打包后application.yml中的server.port和server.context-path配置失效,所以要在pom中配置项目名

```po'm
  <build>
    <!-- 使用外部Tomcat部署访问时, server.port和server.servlet.context-path将失效,端口号为外置tomcat的端口号,上下文为<finalName>中的配置 -->
    <finalName>/demo</finalName>
  	<plugins>
  		<plugin>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-maven-plugin</artifactId>
  		</plugin>
  		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
			</configuration>
		</plugin>
  	</plugins>
  </build>
```

- 5.启动类继承SpringBootServletInitializer

```java
@SpringBootApplication
@MapperScan("com.gengniao.lombok.mapper")
public class Application extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {		
		builder.sources(this.getClass());		
		return super.configure(builder); 	
	}

}
```

- 6.打包项目

```maven
clean package -DskipTests
```

## 项目部署

- 启动日志

![](https://i.imgur.com/G0fUP9A.png)

- 前台测试

![](https://i.imgur.com/FFPz4S5.png)




