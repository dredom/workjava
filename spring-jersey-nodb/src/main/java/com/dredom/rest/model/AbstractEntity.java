package com.dredom.rest.model;

import java.io.Serializable;

/**
 * Hibernate entity model classes extend this.
 *
 * @author andre
 *
 * @param T id type
 */
public abstract class AbstractEntity<T extends Serializable> {

	protected T id;

	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
