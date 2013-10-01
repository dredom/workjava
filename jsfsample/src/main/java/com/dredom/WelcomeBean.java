package com.dredom;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Defaults to "welcomeBean" for JSF.
 */
@ManagedBean
@SessionScoped
public class WelcomeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message = "I am alive!";
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WelcomeBean() {
		System.out.println("WelcomeBean instantiated");
	}
	public String getMessage() {
		return message;
	}
}
