package com.qa.persistence.domain;

import java.util.List;

//import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
//@Data
//@AllArgsConstructor
public class TaskDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String taskName;

	@OneToMany(mappedBy = "myTask", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<SubtaskDomain> subtaskList;

	public TaskDomain() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TaskDomain(String taskName, List<SubtaskDomain> subtaskList) {
		super();
		this.taskName = taskName;
		this.subtaskList = subtaskList;
	}

	public TaskDomain(long id, String taskName, List<SubtaskDomain> subtaskList) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.subtaskList = subtaskList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<SubtaskDomain> getSubtaskList() {
		return subtaskList;
	}

	public void setSubtaskList(List<SubtaskDomain> userList) {
		this.subtaskList = userList;
	}

}