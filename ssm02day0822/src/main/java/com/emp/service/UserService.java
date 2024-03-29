package com.emp.service;

import java.util.Set;
import com.emp.entity.User;

public interface UserService {
	//依据用户名查找用户
	User queryUser(String username);
	
	//添加用户(注册使用)
	void addUser(User user);
	
	//依据用户名查询用户的所有角色
	Set<String> queryRoles(String username);
	
	//依据用户名查找用户的所有权限
	Set<String> queryPermissions(String username);

}
