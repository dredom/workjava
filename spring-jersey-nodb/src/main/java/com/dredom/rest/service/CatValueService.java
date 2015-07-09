package com.dredom.rest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dredom.rest.dao.CatDao;
import com.dredom.rest.model.Cat;
import com.dredom.rest.pojo.CatDto;

@Component
public class CatValueService implements ValueService<CatDto> {

	@Autowired
	private CatDao catDao;

	@Override
    public String getValue() {
		return "Tabby Cat";
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

    @Override
    public CatDto[] getAll() throws NotFoundException {
        System.out.println(getClass().getSimpleName() + " getAll()");
        Cat[] cats = catDao.get();
        if (cats == null) {
            throw new NotFoundException("No cats");
        }
        CatDto[] out = new CatDto[cats.length];
        for (int i = 0; i < cats.length; i++) {
            out[i] = toCatDto(cats[i]);
        }
        return out;
    }

    private CatDto toCatDto(Cat cat) {
        CatDto dto = new CatDto();
        dto.setId(cat.getId());
        dto.setName(cat.getName());
        return dto;
    }

}
