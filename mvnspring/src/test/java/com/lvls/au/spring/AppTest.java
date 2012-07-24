package com.lvls.au.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App with default location for context xml config.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppTest {

	@Autowired
	private SayHello hello;

	@Test
	public void test1() throws Exception {
		System.out.println("test...");
		hello.greet();
	}
}
