package com.WRan.model;

public class UserFavoriteTask {
	public Integer user_id;
	public Integer task_id;
	public Integer uft_id;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public Integer getUft_id() {
		return uft_id;
	}	
	public void setUft_id(Integer uft_id) {
		this.uft_id = uft_id;
	}
	
	@Override
	public String toString() {
		return "userFavoriteTask";
	}
	
}
