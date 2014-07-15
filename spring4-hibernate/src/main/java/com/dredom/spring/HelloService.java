package com.dredom.spring;

import java.util.Properties;

public class HelloService {

	private String name;
	private Properties messages;

	public void greet(String greeting) {
		String message = messages.getProperty("today");
		System.out.println(greeting + " " + name + "\t\t" + message);
	}

	public void setName(String greeting) {
		this.name = greeting;
	}

	public void setMessages(Properties messages) {
		this.messages = messages;
	}


}
