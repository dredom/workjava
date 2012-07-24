package com.lvls.au.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App with specific location for context config.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/application-context.xml")
public class App2Test {

	@Autowired
	private SayHello hello;

	@Test
	public void test1() throws Exception {
		System.out.println("test...");
		hello.greet();
	}
}
