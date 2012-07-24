package com.lvl.au.rest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Spring forth...");
//		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
//		SayHello hello = (SayHello) factory.getBean("hello");
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"});
		SayHello hello =  (SayHello) ctx.getBean("hello", SayHello.class);
		hello.greet();
	}

}
