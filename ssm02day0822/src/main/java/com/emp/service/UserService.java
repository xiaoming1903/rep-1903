package com.emp.service;

import java.util.Set;
import com.emp.entity.User;

public interface UserService {
	//�����û��������û�
	User queryUser(String username);
	
	//����û�(ע��ʹ��)
	void addUser(User user);
	
	//�����û�����ѯ�û������н�ɫ
	Set<String> queryRoles(String username);
	
	//�����û��������û�������Ȩ��
	Set<String> queryPermissions(String username);

}
