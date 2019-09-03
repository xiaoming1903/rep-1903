package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Dept;


public interface DeptDao {
	//��ѯ����
	@Select("select deptno,dname,location from t_dept")
	@Results(id="DeptMapper",value={
			@Result(id=true,column="deptno",property="deptno"),
			@Result(column="dname",property="dname"),
			@Result(column="location",property="location"),
	})
	List<Dept> queryAll();
	
	//���ݱ�Ų�ѯ
	@Select("select deptno,dname,location from t_dept where deptno=#{deptno}")
	Dept queryById(@Param("deptno")String deptno);
	
	//����
	@Insert("insert into t_dept values(#{deptno},#{dname},#{location})")
	void addDept(Dept dept);
	
	//�޸�, ���������޸�
	@Update("update t_dept set dname=#{dname},location=#{location}"
			+ " where deptno=#{deptno}")
	void updateDept(Dept dept);
	
	//ɾ��
	@Delete(" delete from t_dept where deptno=#{deptno}")
	void deleteDept(String deptno);
	

}
