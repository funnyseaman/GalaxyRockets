package com.WRan.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */

public class Tag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private String tagName;
	private Set tasktags = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** full constructor */
	public Tag(String tagName, Set tasktags) {
		this.tagName = tagName;
		this.tasktags = tasktags;
	}

	// Property accessors

	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set getTasktags() {
		return this.tasktags;
	}

	public void setTasktags(Set tasktags) {
		this.tasktags = tasktags;
	}

}