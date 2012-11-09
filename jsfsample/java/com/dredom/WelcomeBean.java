package com.dredom;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "welcome", eager = true)
public class WelcomeBean {
	public WelcomeBean() {
		System.out.println("WelcomeBean instantiated");
	}
	public String getMessage() {
		return "I am alive!";
	}
}
