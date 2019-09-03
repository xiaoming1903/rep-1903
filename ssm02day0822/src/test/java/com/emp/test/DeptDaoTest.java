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
	
	//查询所有 
	@Test
	public void testQueryAll() {
		List<Dept> dept=deptDao.queryAll();
		for(Dept d:dept){
			System.out.println(d);
		}
	}

	//根据编号查询
	@Test
	public void testQueryById() {
		Dept dept=deptDao.queryById("d003");
		System.out.println(dept);
	}

	//增加
	@Test
	public void testAddDept() {
		Dept d=new Dept();
		d.setDeptno("d005");
		d.setDname("敢死部");
        d.setLocation("深山老林");	
        deptDao.addDept(d);
        System.out.println("添加成功了!");
	}

	//修改
	@Test
	public void testUpdateDept() {
		Dept d=deptDao.queryById("d005");
		System.out.println("修改前:"+d);
		d.setDname("缉毒部");
		d.setLocation("大阪");
		System.out.println("修改后:"+d);
		
	}

	//删除
	@Test
	public void testDeleteDept() {
		deptDao.deleteDept("d005");
		System.out.println("删除成功!");
	}
	
	
	//懒加载模式
	@Resource
	private DeptLazyDao deptLazyDao;
	
	@Test//测试懒加载id
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
	
	@Test//测试懒加载查询所有
	public void testLazy2(){
		List<Dept> de=deptLazyDao.queryAll();
		for(Dept d:de){
			System.out.println(d.getDname());
			System.out.println("~~~|~~~~~~~~~~~|~~~");
			//打印部门下所的员工姓名
			List<Emp> es=d.getEmps();
			for(Emp e:es){
				System.out.println(e.getEname());
			}
			System.out.println("~~~~~~~~~~|~~~~~~~");
		}
	}
	
	
	

}


