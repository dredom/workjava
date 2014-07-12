package com.dredom.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main {
	private static String configLocation = "spring-main.xml";
	private static String name = "Joe";

	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation );
		HelloService service = context.getBean("helloService", HelloService.class);
		service.greet(name);
	}
}
