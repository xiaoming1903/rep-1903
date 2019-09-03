package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService {
	@Resource
	private DeptDao deptDao;
	
	@Override
	public List<Dept> queryAll() {
		List<Dept> ds = deptDao.queryAll();
		return ds;
	}

	@Override
	public Dept queryById(String deptno) {
		Dept d = deptDao.queryById(deptno);
		return d;
	}


	@Override
	public void addDept(Dept dept) {
		deptDao.addDept(dept);
		System.out.println("添加成功!");
	}

	@Override
	public void updateDept(Dept dept) {
		deptDao.updateDept(dept);
		System.out.println("更新成功!");
	}

	@Override
	public void deleteDept(String dept) {
		deptDao.deleteDept(dept);
		System.out.println("删除成功!");
	}

	

}
