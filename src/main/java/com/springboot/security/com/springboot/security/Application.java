package com.springboot.security.com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import com.springboot.security.com.springboot.security.oespecific.IHelloWorldService;
import com.springboot.security.com.springboot.security.oespecific.MatcherClass;
import com.springboot.security.com.springboot.security.oespecific.HelloWorldOE;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	//@ConditionalOnProperty(prefix = "test",name = "tenant", havingValue = "TA")
	@Conditional(MatcherClass.class)
	public IHelloWorldService getHelloWorld() {
		return new HelloWorldOE();
	}
}
