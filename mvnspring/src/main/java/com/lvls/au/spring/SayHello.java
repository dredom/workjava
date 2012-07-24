package com.lvls.au.spring;

import org.apache.log4j.Logger;


public class SayHello {

	private String name;
	private String daytime;

	private static final Logger log = Logger.getLogger(SayHello.class);

	public void greet() {
		System.out.printf("Hello %s! %s \n", getName(), getDaytime());
		log.info("done");
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getDaytime() {
		return daytime;
	}

	public final void setDaytime(String daytime) {
		this.daytime = daytime;
	}
}
