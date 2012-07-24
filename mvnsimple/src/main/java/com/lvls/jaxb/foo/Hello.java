package com.lvls.jaxb.foo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hello {
	private Integer id;
	private String greeting;

	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getGreeting() {
		return greeting;
	}
	public final void setGreeting(String greeting) {
		this.greeting = greeting;
	}

}
