package com.dredom;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class WelcomeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message = "I am alive!";
	
	public WelcomeBean() {
		System.out.println("WelcomeBean instantiated");
	}
	public String getMessage() {
		return message;
	}
}
