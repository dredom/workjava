package com.dredom.rest.dao;

import org.springframework.stereotype.Component;

import com.dredom.rest.model.Cat;

/**
 * Hibernate Data Access Object for Cat.
 * @author auntiedt
 */
@Component
public class CatDao extends AbstractDao<Cat> {

    private Integer idGenerator = 0;

    @Override
    protected Cat nextId(Cat entity) {
        idGenerator += 1;
        entity.setId(idGenerator);
        return entity;
    }
}
