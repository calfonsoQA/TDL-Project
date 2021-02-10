package com.qa.persistence.dtos;

import javax.persistence.Column;

public class SubtaskDTO {

	private Long Id;

	private String subtaskDescription;

	private int effortLevel;

	private boolean done;

	public SubtaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubtaskDTO(Long id, String subtaskDescription, int effortLevel, boolean done) {
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

	public int getEffortLevel() {
		return effortLevel;
	}

	public void setEffortLevel(int effortLevel) {
		this.effortLevel = effortLevel;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
