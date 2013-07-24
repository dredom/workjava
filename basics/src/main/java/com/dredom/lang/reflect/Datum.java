package com.dredom.lang.reflect;

public class Datum {
	private Integer id;
	private String text;

	public Datum() {
		super();
	}
	public Datum(Integer id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
