package com.lvls.freemarker;

public class MyBean {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getBigName(String name) {
		return "BIG" + name;
	}
}
