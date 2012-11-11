package com.dredom.json;

import java.util.List;

public class Category {
	public static String TAG = "tag";

	private Integer id;
	private String name;
	private List<SubCat> subCategories;

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
	public List<SubCat> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCat> subCategories) {
		this.subCategories = subCategories;
	}

}
