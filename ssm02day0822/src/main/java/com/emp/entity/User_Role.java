package com.emp.entity;

public class User_Role {
	private int user_id;
	private int role_id;

	public User_Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User_Role(int user_id, int role_id) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "User_Role [user_id=" + user_id + ", role_id=" + role_id + "]";
	}

}
