# spring-boot �������������demo

## 1.pom.xml �ļ�
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

       <!-- ���� -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <!-- ֻ��test������������ -->
           <scope>test</scope>
       </dependency>

    </dependencies>

    <build>
        <finalName>spring-boot-scheduled-task</finalName>
        <plugins>
            <!-- jdk������ -->
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

## 2.�������������
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
		System.out.println("����1,��ǰʱ��:"+MAT.format(new Date()));
	}
	
	@Scheduled(cron = "${jobs.cron}")
	public void getTask2() {
		System.out.println("����2,��ǰʱ��:"+MAT.format(new Date()));
	}
}
```

## 3.application.properties
```properties
jobs.fixedDelay=5000
jobs.cron=0/5 * *   * * ?
```