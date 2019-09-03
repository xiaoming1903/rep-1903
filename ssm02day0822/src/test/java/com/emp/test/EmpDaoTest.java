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

	//查询所有员工
	@Test
	public void testQueryAll() {
		List<Emp> emps=empDao.queryAll();
		for(Emp e:emps){
			System.out.println("名字是:"+e.getEname()+", 部门:"+(e.getDept()==null?"null":e.getDept().getDname())
					+",经理是:"+e.getMgr().getEname());
			//System.out.println(e);
		}
	}

	//依据员工编号查询员工
	@Test
	public void testQueryById(){
	     Emp emp=empDao.queryById("e001");
	     System.out.println(emp);
	}
	
	//依据姓名模糊查查询
	@Test
	public void testQueryLike(){
		List<Emp> emp=empDao.queryLikeName("熊");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	//添加员工
	@Test
	public void testAddEmp(){
		//创建一个员工对象
		Emp emp=new Emp();
		emp.setEmpno("e013");
		emp.setEname("李白");
		emp.setEsex("男");
		emp.setEage(22);
		emp.setEsalary(5000f);
		//创建一个部门对象
		Dept dept=new Dept();
		dept.setDeptno("d001");
		emp.setDept(dept);
		//创建经理对象
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		emp.setMgr(mgr);
		//将e对象保存到数据库
		empDao.addEmp(emp);
		System.out.println("添加OK啦!");
	}
	
	//修改员工
	@Test
	public void testUpdate(){
		Emp emp =empDao.queryById("e013");
		System.out.println("修改前:"+emp);
		emp.setEsalary(10022f);
		emp.getDept().setDeptno("d001");
		emp.getMgr().setEmpno("e007");
		//将修改的数据更新到数据库中
		empDao.updateEmp(emp);
		System.out.println("修改后:"+emp);
	}
	
	
	//删除员工
	@Test
	public void testDele(){
		empDao.deleteEmp("e013");
		System.out.println("删除成功!");
	}
	
	
	//依据部门编号查询部门下所有的员工
	@Test
	public void testEmpAll(){
		List<Emp> emp=empDao.queryByIdAll("d001");
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	 //查询出所有的经理
	@Test
	public void testQueryMgr(){
		List<Emp> emp=empDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	
	@Resource
	private EmpLazyDao empLazyDao;
	
	@Test//测试懒加载
	public void testLazy(){
		Emp emp=empLazyDao.queryById("e002");
		System.out.println(emp.getEname());
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println(emp.getDept().getDname());
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println(emp.getMgr().getEname());
	}
	
	@Test//测试懒加载查询所有员工
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
	
	@Test//测试懒加载依据姓名模糊查询
	public void testLazy3(){
		List<Emp> es=empLazyDao.queryLikeName("熊");
		for(Emp emp:es){
			System.out.println(emp.getEname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getDept().getDname());
			System.out.println("~~~~~~~~~~~~~~~~~");
			System.out.println(emp.getMgr().getEname());
		}
	}
	
	@Test//测试懒加载查询出所有的经理
	public void testLazy4(){
		List<Emp> emp=empLazyDao.queryMgrs();
		for(Emp e:emp){
			System.out.println(e);
		}
	}
	
	
	@Test//测试懒加载依据部门编号查询部门下所有的员工
	public void testLazy5(){
		List<Emp> emp=empLazyDao.queryByIdAll("d001");
		for(Emp e:emp){
			System.out.println(e.getEname()+",  "+e.getDept().getDname());
		}
	}
	
}






