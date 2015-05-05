package com.dredom.spring;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dredom.spring.SayHello;

/**
 * Unit test for simple App with default location for context xml config.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppTest {

	@Autowired
	private SayHello hello;

	/**
	 * Fails to find the AppTest-context.xml in the classpath (not in target/).
	 * 08:46:47,336 DEBUG DirtiesContextTestExecutionListener:113 - After test class: context [[TestContext@204ad33f
	 * testClass = AppTest, locations = array<String>['classpath:/com/dredom/spring/AppTest-context.xml'],
	 * testInstance = [null], testMethod = [null], testException = [null]]], dirtiesContext [false].
	 */
	@Ignore
	@Test
	public void test1() throws Exception {
		System.out.println("test...");
		hello.greet();
	}
}
