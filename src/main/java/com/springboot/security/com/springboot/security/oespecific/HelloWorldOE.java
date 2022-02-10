package com.springboot.security.com.springboot.security.oespecific;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class HelloWorldOE implements IHelloWorldService{

	@Override
	public String getHelloWorld() {
		return "From OE side";
	}

	
}
