package com.dredom.spring;

public class HelloService {

	private String greeting;

	public void greet(String name) {
		System.out.println(greeting + " " + name);
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
