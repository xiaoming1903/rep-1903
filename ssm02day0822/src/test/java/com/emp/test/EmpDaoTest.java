package com.emp.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class EmpDaoTest {
	@Resource
	private EmpDao empDao;

	//��ѯ����Ա��
	@Test
	public void testQueryAll() {
		List<Emp> emps=empDao.queryAll();
		for(Emp e:emps){
			System.out.println("������:"+e.getEname()+", ����:"+(e.getDept()==null?"null":e.getDept().getDname())
					+",������:"+e.getMgr().getEname());
			//System.out.println(e);
		}
	}

	//����Ա����Ų�ѯԱ��
	@Test
	public void testQueryById(){
	     Emp emp=empDao.queryById("e001");
	     System.out.println(emp);
	}
	
	//��������ģ�����ѯ
	@Test
	public void testQueryLike(){
		List<Emp> emp=empDao.queryLikeName("��");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	//���Ա��
	@Test
	public void testAddEmp(){
		//����һ��Ա������
		Emp emp=new Emp();
		emp.setEmpno("e013");
		emp.setEname("���");
		emp.setEsex("��");
		emp.setEage(22);
		emp.setEsalary(5000f);
		//����һ�����Ŷ���
		Dept dept=new Dept();
		dept.setDeptno("d001");
		emp.setDept(dept);
		//�����������
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		emp.setMgr(mgr);
		//��e���󱣴浽���ݿ�
		empDao.addEmp(emp);
		System.out.println("���OK��!");
	}
	
	//�޸�Ա��
	@Test
	public void testUpdate(){
		Emp emp =empDao.queryById("e013");
		System.out.println("�޸�ǰ:"+emp);
		emp.setEsalary(10022f);
		emp.getDept().setDeptno("d001");
		emp.getMgr().setEmpno("e007");
		//���޸ĵ����ݸ��µ����ݿ���
		empDao.updateEmp(emp);
		System.out.println("�޸ĺ�:"+emp);
	}
	
	
	//ɾ��Ա��
	@Test
	public void testDele(){
		empDao.deleteEmp("e013");
		System.out.println("ɾ���ɹ�!");
	}
	
	
	//���ݲ��ű�Ų�ѯ���������е�Ա��
	@Test
	public void testEmpAll(){
		List<Emp> emp=empDao.queryByIdAll("d001");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	 //��ѯ�����еľ���
	@Test
	public void testQueryMgr(){
		List<Emp> emp=empDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	
	@Resource
	private EmpLazyDao empLazyDao;
	
	@Test//����������
	public void testLazy(){
		Emp emp=empLazyDao.queryById("e002");
		System.out.println(emp.getEname());
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println(emp.getDept().getDname());
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println(emp.getMgr().getEname());
	}
	
	@Test//���������ز�ѯ����Ա��
	public void testLazy2(){
		List<Emp> es=empLazyDao.queryAll();
		for(Emp emp:es){
			System.out.println(emp.getEname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getDept().getDname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getMgr().getEname());
		}
	}
	
	@Test//������������������ģ����ѯ
	public void testLazy3(){
		List<Emp> es=empLazyDao.queryLikeName("��");
		for(Emp emp:es){
			System.out.println(emp.getEname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getDept().getDname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getMgr().getEname());
		}
	}
	
	@Test//���������ز�ѯ�����еľ���
	public void testLazy4(){
		List<Emp> emp=empLazyDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	
	@Test//�������������ݲ��ű�Ų�ѯ���������е�Ա��
	public void testLazy5(){
		List<Emp> emp=empLazyDao.queryByIdAll("d001");
		for(Emp e:emp){
			System.out.println(e.getEname()+",  "+e.getDept().getDname());
		}
	}
	
}






