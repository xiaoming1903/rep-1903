package com.emp.service;

import java.util.List;

import com.emp.entity.Dept;

public interface DeptService {
	//��ѯ����Ա��
	List<Dept> queryAll();
	
	//���ݲ��ű�Ų�ѯ����
	Dept queryById(String deptno);
	
	
	
	//��Ӳ���
	void addDept(Dept dept);
	
	//�޸Ĳ���
	void updateDept(Dept dept);
	
	//���ݱ��ɾ������
	void deleteDept(String dept);
}
