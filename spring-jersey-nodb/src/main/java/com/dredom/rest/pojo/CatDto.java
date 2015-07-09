package com.dredom.rest.pojo;

public class CatDto {

	private Integer id;
	private String name;
	private CatBreed breed;
	private Boolean rare;

	public final Integer getId() {
		return id;
	}
	public final void setId(Integer id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final CatBreed getBreed() {
		return breed;
	}
	public final void setBreed(CatBreed breed) {
		this.breed = breed;
	}
	public final Boolean getRare() {
		return rare;
	}
	public final void setRare(Boolean rare) {
		this.rare = rare;
	}
}
