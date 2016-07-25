package com.WRan.model;

/**
 * Focus entity. @author MyEclipse Persistence Tools
 */

public class Focus implements java.io.Serializable {

	// Fields

	public Integer focus_id;
	public Integer user_id;
	public Integer focused_user_id;

	// Constructors

	/** default constructor */
	public Focus() {
	}


	public Integer getFocus_id() {
		return focus_id;
	}


	public void setFocus_id(Integer focus_id) {
		this.focus_id = focus_id;
	}


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getFocused_user_id() {
		return focused_user_id;
	}

	public void setFocused_user_id(Integer focused_user_id) {
		this.focused_user_id = focused_user_id;
	}

	@Override
	public String toString() {
		return "Focus";
	}

	

}