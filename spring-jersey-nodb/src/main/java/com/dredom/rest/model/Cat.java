package com.dredom.rest.model;


public class Cat extends AbstractEntity<Integer> {

	private static final long serialVersionUID = -3146438750543621722L;

	private String name;

	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}

}
