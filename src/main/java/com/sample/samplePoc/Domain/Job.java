package com.sample.samplePoc.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sample.samplePoc.enums.JobCategories;

@Entity
@Table(name = "t_job")
public class Job{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column
	private String title;

	@Column
	private String description;

	@Enumerated(EnumType.STRING)
	@Column
	private JobCategories category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public JobCategories getCategory() {
		return category;
	}

	public void setCategory(JobCategories category) {
		this.category = category;
	}

}
