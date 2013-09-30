package com.dredom;


import org.junit.Assert;
import org.junit.Test;

public class WelcomeBeanTest {

	@Test
	public void message() {
		WelcomeBean bean = new WelcomeBean();
		String message = bean.getMessage();
		Assert.assertNotNull(message);
	}
}
