package com.dredom;

import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class StoreCollection {

	@Id
	private String id;
	private String name;
	private Geo geo;
	private String[] tag;
	private String name_lc;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	public String[] getTag() {
		return tag;
	}
	public void setTag(String[] tag) {
		this.tag = tag;
	}
    public String getName_lc() {
        return name_lc;
    }
    public void setName_lc(String name_lc) {
        this.name_lc = name_lc;
    }
    @Override
    public String toString() {
        return  ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
