package com.WRan.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */

public class Task implements java.io.Serializable {

	// Fields

	public Integer task_id;
	public Integer parent_task_id;
	public Integer task_author;
	public Timestamp task_date;
	public String task_text;
	public String task_pic;
	public Integer tree_level;
	public String task_title;
	
	public Integer getTask_id() {
		return task_id;
	}

	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}

	public Integer getParent_task_id() {
		return parent_task_id;
	}

	public void setParent_task_id(Integer parent_task_id) {
		this.parent_task_id = parent_task_id;
	}

	public Integer getTask_author() {
		return task_author;
	}

	public void setTask_author(Integer task_author) {
		this.task_author = task_author;
	}

	public Timestamp getTask_date() {
		return task_date;
	}

	public void setTask_date(Timestamp task_date) {
		this.task_date = task_date;
	}

	public String getTask_text() {
		return task_text;
	}

	public void setTask_text(String task_text) {
		this.task_text = task_text;
	}

	public String getTask_pic() {
		return task_pic;
	}

	public void setTask_pic(String task_pic) {
		this.task_pic = task_pic;
	}

	public Integer getTree_level() {
		return tree_level;
	}

	public void setTree_level(Integer tree_level) {
		this.tree_level = tree_level;
	}

	public String getTask_title() {
		return task_title;
	}

	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}

	@Override
	public String toString() {
		return "Task";
	}
	
	
}