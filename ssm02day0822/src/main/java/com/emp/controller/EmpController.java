package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.Dept;
import com.emp.entity.Emp;
import com.emp.service.DeptService;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;

@Controller
public class EmpController {
	
	//ע��ҵ������
	@Resource
	private EmpService empService;
	
	@Resource
	private DeptService deptService;
	
	//��������
		public void loadData(HttpSession session){
			//���������Ա�radio��map
			Map<String,String> map = new HashMap<String,String>();
			map.put("��", "��");
			map.put("Ů", "Ů");
			//���������б���
			session.setAttribute("map", map);
			//���ɲ�������
			List<Dept> depts = deptService.queryAll();
			session.setAttribute("depts", depts);
			//���ɾ�������
			List<Emp> mgrs = empService.queryMgrs();
			session.setAttribute("mgrs", mgrs);
		}
	
	//��ҳ��ѯ
	@RequestMapping("/emp/list")
	public String queryByPage(
	@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
	@RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize,
	Model model){
		PageBean<Emp> pageBean=empService.queryByPage(pageNo, pageSize);
		//
		model.addAttribute("pageBean", pageBean);
		return "ListEmp";
	}
	
	//������ҳ��ѯ
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.OR)
	public String queryCondition(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize, 
			@RequestParam(value="cd",required=false,defaultValue="")String cd,
			HttpSession session){
		     //ȥ���ո� 
		    cd=cd.trim();
		     PageBean<Emp> pageBean= empService.queryByCondition(pageNo, pageSize, cd);
		   //��pageBean��cd���뵽������
		     session.setAttribute("pageBean",pageBean);
		     session.setAttribute("cd",cd);
		return "ListEmp2";
	}
	
	//���ݱ�Ų�ѯԱ��
	@RequestMapping("/emp/byId")
	public String queryEmpById(@RequestParam(value="empno",required=false)String empno){
		
		return "";
	}
	
	//���Ա�� ,��ת������ҳ��
	@RequestMapping("/emp/toAdd")
	public String toAdd(@ModelAttribute("emp")Emp emp,HttpSession session){
		loadData(session);
		return "AddEmp";
	}
	
	//���Ա��
		@RequestMapping(value="/emp",method=RequestMethod.POST)
		public String addEmp(Emp emp){
			String empno = UUID.randomUUID().toString();
			emp.setEmpno(empno);
			empService.addEmp(emp);		
			return "redirect:/emp/conditionList";
		}
	
		//��ת���޸�ҳ��
		@RequestMapping("/emp/toUpdate")
		public String toUpdateEmp(@RequestParam("empno")String empno,
				 @ModelAttribute("emp") Emp emp,
				 Model model,HttpSession session){
			loadData(session);
		    emp = empService.queryEmpById(empno);
			model.addAttribute("emp", emp);
			return "UpdateEmp";
		}
			
		//�޸�Ա��
		@RequestMapping(value="/emp",method=RequestMethod.PUT)
		public String updateEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
			//�����޸���Ϣ�����ݿ���
			 empService.updateEmp(emp);
			 //��session��ȡ��pageBean�Ͳ�ѯ����
			 PageBean<Emp> pageBean=(PageBean<Emp>) session.getAttribute("pageBean");
			 String  cd = (String) session.getAttribute("cd");
			 //����pageBean��list
			 pageBean=empService.queryByCondition(pageBean.getPageNo(),
					 pageBean.getPageSize(),cd);
			 //��PageBean�������·Żص�session��
			 session.setAttribute("pageBean", pageBean);
			 return "redirect:/emp/reList";
		}
		
		@RequestMapping("/emp/reList")
		public String toEmpList(){
			return "ListEmp2";
		}
		
		
		//ɾ��Ա��
		@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
		public String delete(@PathVariable("empno") String empno){
			   empService.deleteEmp(empno);
			   return "redirect:/emp/conditionList";
		}
	
}



