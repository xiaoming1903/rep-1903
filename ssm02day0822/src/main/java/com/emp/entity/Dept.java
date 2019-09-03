package com.emp.entity;

import java.util.List;

//������
public class Dept {
	private String deptno;// ���ű��
	private String dname;// ��������
	private String location;//���ŵ�ַ
	private List<Emp> emps;//ĳ�������µ�����Ա��
	//many=@Many
	
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dept(String deptno, String dname, String location, List<Emp> emps) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.location = location;
		this.emps = emps;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Emp> getEmps() {
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", location=" + location + ", emps=" + emps + "]";
	}


	
}
