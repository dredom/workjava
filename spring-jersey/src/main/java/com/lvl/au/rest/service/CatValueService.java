package com.lvl.au.rest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lvl.au.rest.dao.CatDao;
import com.lvl.au.rest.model.Cat;
import com.lvl.au.rest.pojo.CatDto;

@Component
public class CatValueService implements ValueService<CatDto> {

//	@Autowired
	private CatDao catDao;

	@Override
	public String getValue() {
		return "Tabby";
	}

	@Override
	public CatDto add(CatDto value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CatDto get(Integer id) throws NotFoundException {
		Cat entity = catDao.load(id);
		if (entity == null) {
			throw new NotFoundException(id.toString());
		}
		CatDto dto = new CatDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
