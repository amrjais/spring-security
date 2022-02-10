package com.springboot.security.com.springboot.security.oespecific;

import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
public class HelloWorldDefault implements IHelloWorldService{

	@Override
	public String getHelloWorld() {
		return "Hello World";
	}

}
