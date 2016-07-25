package com.WRan.model;

/**
 * Tasktag entity. @author MyEclipse Persistence Tools
 */

public class Tasktag implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private Tag tag;
	private Task task;

	// Constructors

	/** default constructor */
	public Tasktag() {
	}

	/** full constructor */
	public Tasktag(Tag tag, Task task) {
		this.tag = tag;
		this.task = task;
	}

	// Property accessors

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}