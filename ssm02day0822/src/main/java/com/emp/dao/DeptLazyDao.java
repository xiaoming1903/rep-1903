package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Dept;

//������Dept
public interface DeptLazyDao {
	
	//���ݲ��ű��id��ѯ
	@Select("select deptno,dname,location from t_dept where deptno=#{deptno}")
	@Results(id="DeptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),	
			@Result(column="dname",property="dname"),	
			@Result(column="location",property="location"),	
			@Result(column="deptno",property="emps",
		    many=@Many(select="com.emp.dao.EmpDao.queryByIdAll",
		    		fetchType=FetchType.LAZY))
	      })
	Dept queryById(@Param("deptno")String deptno);
	
	//��ѯ���еĲ���
	@Select("select deptno,dname,location from t_dept")
	@ResultMap(value="DeptMapper")
	List<Dept> queryAll();
    
	
	//����
	@Insert("insert into t_dept values(#{deptno},#{dname},#{location})")
	void addDept(Dept dept);
	
	
 }






