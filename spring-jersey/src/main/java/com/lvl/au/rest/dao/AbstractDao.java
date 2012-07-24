package com.lvl.au.rest.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lvl.au.rest.model.AbstractEntity;


/**
 *
 * @param <T> entity type
 */
public class AbstractDao<T extends AbstractEntity<? extends Serializable>> extends HibernateDaoSupport {
	protected final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		LogFactory.getLog(this.getClass()).debug("LOAD["+id+"]");
		return (T) getSession().load(entityClass, id);
	}

	public T save(T entity) {
		LogFactory.getLog(this.getClass()).debug("SAVE["+entity+"]");
		getSession().saveOrUpdate(entity);
		getSession().flush();
		return entity;
	}

	public T update(T entity) {
		LogFactory.getLog(this.getClass()).debug("UPDATE["+entity+"]");
		getSession().update(entity);
		return entity;
	}

	public void delete(T entity) {
		LogFactory.getLog(this.getClass()).debug("DELETE["+entity+"]");
		getSession().delete(entity);
	}

	public Criteria criteria() {
		return getSession().createCriteria(entityClass);
	}

}
