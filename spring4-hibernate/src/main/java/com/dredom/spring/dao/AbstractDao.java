package com.dredom.spring.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.dredom.spring.model.AbstractEntity;



/**
 *
 * @param <T> entity type
 */
public class AbstractDao<T extends AbstractEntity<? extends Serializable>>  {
	protected final Class<T> entityClass;

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		LogFactory.getLog(this.getClass()).debug("LOAD["+id+"]");
		return (T) currentSession().load(entityClass, id);
//		Session session = sessionFactory.openSession();
//		T result = (T) session.load(entityClass, id);
//		session.close();
//		return result;
	}

	public T save(T entity) {
		LogFactory.getLog(this.getClass()).debug("SAVE["+entity+"]");
		currentSession().saveOrUpdate(entity);
		currentSession().flush();
		return entity;
	}

	public T update(T entity) {
		LogFactory.getLog(this.getClass()).debug("UPDATE["+entity+"]");
		currentSession().update(entity);
		return entity;
	}

	public void delete(T entity) {
		LogFactory.getLog(this.getClass()).debug("DELETE["+entity+"]");
		currentSession().delete(entity);
	}

	public Criteria criteria() {
		return currentSession().createCriteria(entityClass);
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
