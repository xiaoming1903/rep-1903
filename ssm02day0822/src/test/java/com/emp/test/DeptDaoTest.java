package com.emp.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.dao.DeptLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DeptDaoTest {

	@Resource
	private DeptDao deptDao;
	
	//��ѯ���� 
	@Test
	public void testQueryAll() {
		List<Dept> dept=deptDao.queryAll();
		for(Dept d:dept){
			System.out.println(d);
		}
	}

	//���ݱ�Ų�ѯ
	@Test
	public void testQueryById() {
		Dept dept=deptDao.queryById("d003");
		System.out.println(dept);
	}

	//����
	@Test
	public void testAddDept() {
		Dept d=new Dept();
		d.setDeptno("d005");
		d.setDname("������");
        d.setLocation("��ɽ����");	
        deptDao.addDept(d);
        System.out.println("��ӳɹ���!");
	}

	//�޸�
	@Test
	public void testUpdateDept() {
		Dept d=deptDao.queryById("d005");
		System.out.println("�޸�ǰ:"+d);
		d.setDname("������");
		d.setLocation("����");
		System.out.println("�޸ĺ�:"+d);
		
	}

	//ɾ��
	@Test
	public void testDeleteDept() {
		deptDao.deleteDept("d005");
		System.out.println("ɾ���ɹ�!");
	}
	
	
	//������ģʽ
	@Resource
	private DeptLazyDao deptLazyDao;
	
	@Test//����������id
	public void testLazy1(){
		Dept d=deptLazyDao.queryById("d001");
		System.out.println(d.getDname());
		System.out.println("~~~|~~~~~~~~~~~|~~~");
		List<Emp> emp=d.getEmps();
		for(Emp e:emp){
			System.out.println(e.getEname());
		}
		System.out.println("~~~~~~~~~~|~~~~~~~");
	}
	
	@Test//���������ز�ѯ����
	public void testLazy2(){
		List<Dept> de=deptLazyDao.queryAll();
		for(Dept d:de){
			System.out.println(d.getDname());
			System.out.println("~~~|~~~~~~~~~~~|~~~");
			//��ӡ����������Ա������
			List<Emp> es=d.getEmps();
			for(Emp e:es){
				System.out.println(e.getEname());
			}
			System.out.println("~~~~~~~~~~|~~~~~~~");
		}
	}
	
	
	

}


