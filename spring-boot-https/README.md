# https 服务的搭建

## 1.获取 https 证书
+ 1.申请 便宜SSL证书 `keystore.jks`
    + 注意证书对应的地址要和云服务器地址一致,这里为**`www.kzby.xyz`**
+ 2.搭建**`Spring-boot`**项目,在`application.xml`中配置**SSL证书**
```properties
# the port of server
server.port=8081
server.ssl.key-store=keystore.jks
server.ssl.key-store-password=82215976767
server.ssl.keyStoreType=jks
# It's wrong to add the keyAlias of the certificate
# server.ssl.keyAlias:tomcat
```
    + 这里 **keystore.jks** 对应的windows地址为工作空间所在根目录下,对应**linux**服务器地址为打**jar**包后的**jar包**所在目录

## maven项目打包要求
+ 1.需要**spring-boot-maven-plugin
```java
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<configuration>
		<fork>true</fork>
		<mainCalss>com.kongzi.https.Application</mainCalss>
	</configuration>
</plugin>
```
+ 2.**&lt;mainClass&gt;** 中加载主类
+ 3.**&lt;fork&gt;true&lt;/fork&gt;**
+ 4.Application.java需要继承 **SpringBootServletInitializer**
```java
    @SpringBootApplication(scanBasePackages = "com.kongzi.https")
    public class Application extends SpringBootServletInitializer{

    	public static void main(String[] args) {
    		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
    		builder.bannerMode(Mode.OFF);
    		builder.run(args);
    	}
    	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    		return builder.sources(this.getClass());
    	}
    }
 ```
## maven 项目打包
+ Eclips 命令: **`clean package`**