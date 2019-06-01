package com.sample.samplePoc.beans;

import java.util.Set;

public class JobSearchBean {

	private Set<Long> ids;

	private String title;

	private String description;

	private Set<String> category;
	
	private Integer limit;
	
	private Integer offset;

	public JobSearchBean() {

	}

	public JobSearchBean(Set<Long> ids, String title, String description, Set<String> category,Integer offset,Integer limit) {
		super();
		this.ids = ids;
		this.title = title;
		this.description = description;
		this.category = category;
		this.offset = offset;
		this.limit = limit;
	}

	public Set<Long> getIds() {
		return ids;
	}

	public void setIds(Set<Long> ids) {
		this.ids = ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getCategory() {
		return category;
	}

	public void setCategory(Set<String> category) {
		this.category = category;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	

}