package com.dredom.rest.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

import com.dredom.rest.model.AbstractEntity;


/**
 *
 * @param <T> entity type
 */
public abstract class AbstractDao<T extends AbstractEntity<? extends Serializable>>  {


	private Map<Serializable, T> db = new HashMap<Serializable, T>();


	public T load(Serializable id) {
		LogFactory.getLog(this.getClass()).debug("LOAD["+id+"]");
		return db.get(id);
	}

	public T[] get() {
	    System.out.println(getClass().getSimpleName() + " get()");
	    Collection<T> list = db.values();
	    if (list.size() == 0) {
	        System.out.println(getClass().getSimpleName() + " get() null");
	        return null;
	    }
	    System.out.println(getClass().getSimpleName() + " get() return");
	    return (T[]) list.toArray();
	}

	public T add(T entity) {
		LogFactory.getLog(this.getClass()).debug("SAVE["+entity+"]");
		T entityWithId = nextId(entity);
		db.put(entityWithId.getId(), entityWithId);
		return entityWithId;
	}

	public T update(T entity) {
		LogFactory.getLog(this.getClass()).debug("UPDATE["+entity+"]");
		db.put(entity.getId(), entity);
		return entity;
	}

	public void delete(T entity) {
		LogFactory.getLog(this.getClass()).debug("DELETE["+entity+"]");
		db.remove(entity.getId());
	}

	protected abstract T nextId(T entity);

}
