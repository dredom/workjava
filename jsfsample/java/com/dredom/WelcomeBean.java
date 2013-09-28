package com.dredom;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "welcome", eager = true)
@SessionScoped
public class WelcomeBean implements Serializable {
	public WelcomeBean() {
		System.out.println("WelcomeBean instantiated");
	}
	public String getMessage() {
		return "I am alive!";
	}
}
