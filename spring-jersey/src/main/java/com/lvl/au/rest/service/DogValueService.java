package com.lvl.au.rest.service;

import org.springframework.stereotype.Component;

import com.lvl.au.rest.pojo.DogDto;
import com.lvl.au.rest.pojo.DogBreed;

@Component
public class DogValueService implements ValueService<DogDto> {

	@Override
	public String getValue() {
		return DogBreed.Shepherd.toString();
	}

	@Override
	public DogDto add(DogDto value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DogDto get(Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
