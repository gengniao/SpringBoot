# spring-boot 的任务调度入门demo

## 1.pom.xml 文件
```pom
<parent>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-parent</artifactId>
  	<version>2.0.0.RELEASE</version>
  	<relativePath/>
  </parent>
  
  <properties>
  	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  	<java.version>1.8</java.version>
  </properties>
  
  <dependencies>
       <!-- web -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>

       <!-- 测试 -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <!-- 只在test测试里面运行 -->
           <scope>test</scope>
       </dependency>

    </dependencies>

    <build>
        <finalName>spring-boot-scheduled-task</finalName>
        <plugins>
            <!-- jdk编译插件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

## 2.任务调度配置类
```java
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
	
	private final static SimpleDateFormat MAT= new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedDelayString = "${jobs.fixedDelay}")
	public void getTask1() {
		System.out.println("任务1,当前时间:"+MAT.format(new Date()));
	}
	
	@Scheduled(cron = "${jobs.cron}")
	public void getTask2() {
		System.out.println("任务2,当前时间:"+MAT.format(new Date()));
	}
}
```

## 3.application.properties
```properties
jobs.fixedDelay=5000
jobs.cron=0/5 * *   * * ?
```