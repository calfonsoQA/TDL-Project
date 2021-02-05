package com.qa.persistence.domain;


//import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
//@Data
//@AllArgsConstructor
public class SubtaskDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String subtaskDescription;
    
    @Column
    private int effortLevel;

    @Column
    private boolean done;
    
    @ManyToOne
    private TaskDomain myTask;

    // Default constructor
    public SubtaskDomain() {
        super();
    }

	public SubtaskDomain(String subtaskDescription, int effortLevel, boolean done,
			TaskDomain myTask) {
		super();
		this.subtaskDescription = subtaskDescription;
		this.effortLevel = effortLevel;
		this.done = done;
		this.myTask = myTask;
	}

	public SubtaskDomain(long id, String subtaskDescription, int effortLevel, boolean done,
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

	public void setId(long id) {
		this.id = id;
	}

	public String getSubtaskDescription() {
		return subtaskDescription;
	}

	public void setSubtaskDescription(String subtaskDescription) {
		this.subtaskDescription = subtaskDescription;
	}

	public int isEffortLevel() {
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

	public TaskDomain getMyTask() {
		return myTask;
	}

	public void setMyTask(TaskDomain myTask) {
		this.myTask = myTask;
	}
    
    

    

}