package com.lvl.au.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "dog")
public class Dog extends AbstractEntity<Integer> {

	private static final long serialVersionUID = -3146438750543621722L;

	@Column
	private String name;

	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}

}
