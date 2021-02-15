package com.qa.persistence.dtos;

public class SubtaskDTO {

	private Long Id;

	private String subtaskDescription;

	private Integer effortLevel;

	private Boolean done;

	public SubtaskDTO() {
		super();
	}

	public SubtaskDTO(Long id, String subtaskDescription, Integer effortLevel, Boolean done) {
		super();
		Id = id;
		this.subtaskDescription = subtaskDescription;
		this.effortLevel = effortLevel;
		this.done = done;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getSubtaskDescription() {
		return subtaskDescription;
	}

	public void setSubtaskDescription(String subtaskDescription) {
		this.subtaskDescription = subtaskDescription;
	}

	public Integer getEffortLevel() {
		return effortLevel;
	}

	public void setEffortLevel(Integer effortLevel) {
		this.effortLevel = effortLevel;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

}
