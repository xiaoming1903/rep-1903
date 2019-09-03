package com.emp.entity;

public class Role_Permission {
	private int role_id;
	private int permission_id;

	public Role_Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role_Permission(int role_id, int permission_id) {
		super();
		this.role_id = role_id;
		this.permission_id = permission_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}

	@Override
	public String toString() {
		return "Role_Permission [role_id=" + role_id + ", permission_id=" + permission_id + "]";
	}

}
