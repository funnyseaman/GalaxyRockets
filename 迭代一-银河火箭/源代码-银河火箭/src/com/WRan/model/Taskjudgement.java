package com.WRan.model;

/**
 * Taskjudgement entity. @author MyEclipse Persistence Tools
 */

public class Taskjudgement implements java.io.Serializable {

	// Fields

	private Integer judgementId;
	private User user;
	private Task task;

	// Constructors

	/** default constructor */
	public Taskjudgement() {
	}

	/** full constructor */
	public Taskjudgement(User user, Task task) {
		this.user = user;
		this.task = task;
	}

	// Property accessors

	public Integer getJudgementId() {
		return this.judgementId;
	}

	public void setJudgementId(Integer judgementId) {
		this.judgementId = judgementId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}