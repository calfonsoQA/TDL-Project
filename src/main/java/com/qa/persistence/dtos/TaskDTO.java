package com.qa.persistence.dtos;

import java.util.List;

public class TaskDTO {

	private Long Id;

	private String taskName;
	
	private List<SubtaskDTO> subtaskList;	
	

	public TaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskDTO(Long id, String taskName, List<SubtaskDTO> subtaskList) {
		super();
		Id = id;
		this.taskName = taskName;
		this.subtaskList = subtaskList;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<SubtaskDTO> getSubtaskList() {
		return subtaskList;
	}

	public void setSubtaskList(List<SubtaskDTO> userList) {
		this.subtaskList = userList;
	}
}
