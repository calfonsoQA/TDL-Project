package com.qa.persistence.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubtaskDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String subtaskDescription;
    
    @Column
    private Integer effortLevel;

    @Column
    private Boolean done;
    
    @ManyToOne
    private TaskDomain myTask;

    // Default constructor
    public SubtaskDomain() {
        super();
    }

	public SubtaskDomain(String subtaskDescription, Integer effortLevel, Boolean done,
			TaskDomain myTask) {
		super();
		this.subtaskDescription = subtaskDescription;
		this.effortLevel = effortLevel;
		this.done = done;
		this.myTask = myTask;
	}

	public SubtaskDomain(Long id, String subtaskDescription, Integer effortLevel, Boolean done,
			TaskDomain myTask) {
		super();
		this.id = id;
		this.subtaskDescription = subtaskDescription;
		this.effortLevel = effortLevel;
		this.done = done;
		this.myTask = myTask;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setEffortLevel(Integer effortLevel) {
		this.effortLevel = effortLevel;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public TaskDomain getMyTask() {
		return myTask;
	}

	public void setMyTask(TaskDomain myTask) {
		this.myTask = myTask;
	}
    
    

    

}