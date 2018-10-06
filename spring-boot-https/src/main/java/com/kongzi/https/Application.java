package com.kongzi.https;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.kongzi.https")
public class Application {
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
		
		builder.bannerMode(Mode.OFF);
		
		builder.run(args);
	}
}

