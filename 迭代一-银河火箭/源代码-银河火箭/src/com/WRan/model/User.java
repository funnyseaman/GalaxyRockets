package com.WRan.model;



public class User{

	// Fields

	public Integer user_id;
	public String user_name;
	public String user_password;
	public String user_email;
	public String user_school;
	public String user_major;
	public String user_head;
	public Integer user_sex;
	public String user_sign;



	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_school() {
		return user_school;
	}

	public void setUser_school(String user_school) {
		this.user_school = user_school;
	}

	public String getUser_major() {
		return user_major;
	}

	public void setUser_major(String user_major) {
		this.user_major = user_major;
	}

	public String getUser_head() {
		return user_head;
	}

	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_sign() {
		return user_sign;
	}

	public void setUser_sign(String user_sign) {
		this.user_sign = user_sign;
	}

	/** default constructor */
	public User() {
	}
	public User(String user_name, String user_password, String user_email) {
		super();
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
	}

	public User(Integer user_id, String user_name, String user_password, String user_email, String user_school,
			String user_major, String user_head, Integer user_sex, String user_sign) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_school = user_school;
		this.user_major = user_major;
		this.user_head = user_head;
		this.user_sex = user_sex;
		this.user_sign = user_sign;
	}

	@Override
	public String toString() {
		return "User";
	}

	

}