package com.WRan.model;

/**
 * Focus entity. @author MyEclipse Persistence Tools
 */

public class Focus implements java.io.Serializable {

	// Fields

	private Integer focusId;
	private User userByFocusedUserId;
	private User userByUserId;

	// Constructors

	/** default constructor */
	public Focus() {
	}

	/** full constructor */
	public Focus(User userByFocusedUserId, User userByUserId) {
		this.userByFocusedUserId = userByFocusedUserId;
		this.userByUserId = userByUserId;
	}

	// Property accessors

	public Integer getFocusId() {
		return this.focusId;
	}

	public void setFocusId(Integer focusId) {
		this.focusId = focusId;
	}

	public User getUserByFocusedUserId() {
		return this.userByFocusedUserId;
	}

	public void setUserByFocusedUserId(User userByFocusedUserId) {
		this.userByFocusedUserId = userByFocusedUserId;
	}

	public User getUserByUserId() {
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId) {
		this.userByUserId = userByUserId;
	}

}