package com.sample.samplePoc.DTO;

public class JobDTO {

	private Long id;

	private String title;

	private String description;

	private String category;

	public JobDTO() {
	}

	public JobDTO(Long id, String title, String description, String category) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
