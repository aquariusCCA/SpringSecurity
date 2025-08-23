package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.test.mapper")
public class TestSpringSecurityApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TestSpringSecurityApplication.class, args);
		System.out.println("111");
	}
}
