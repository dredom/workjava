package com.dredom.rest.service;

public interface ValueService<T> {

	String getValue();
	T get(Integer id) throws NotFoundException;
	T[] getAll() throws NotFoundException;
	T add(T value);
	void delete(Integer id);
}
