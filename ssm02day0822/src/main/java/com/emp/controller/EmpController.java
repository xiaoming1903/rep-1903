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
	
	//注入业务层对象
	@Resource
	private EmpService empService;
	
	@Resource
	private DeptService deptService;
	
	//加载数据
		public void loadData(HttpSession session){
			//创建生成性别radio的map
			Map<String,String> map = new HashMap<String,String>();
			map.put("男", "男");
			map.put("女", "女");
			//放作用域中备用
			session.setAttribute("map", map);
			//生成部门数据
			List<Dept> depts = deptService.queryAll();
			session.setAttribute("depts", depts);
			//生成经理数据
			List<Emp> mgrs = empService.queryMgrs();
			session.setAttribute("mgrs", mgrs);
		}
	
	//分页查询
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
	
	//条件分页查询
	@RequestMapping("/emp/conditionList")
	@RequiresPermissions(value={"emp:query","emp:del"},logical=Logical.OR)
	public String queryCondition(
			@RequestParam(value="pageNo",required=false,defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="3")Integer pageSize, 
			@RequestParam(value="cd",required=false,defaultValue="")String cd,
			HttpSession session){
		     //去除空格 
		    cd=cd.trim();
		     PageBean<Emp> pageBean= empService.queryByCondition(pageNo, pageSize, cd);
		   //将pageBean和cd放入到作用域
		     session.setAttribute("pageBean",pageBean);
		     session.setAttribute("cd",cd);
		return "ListEmp2";
	}
	
	//依据编号查询员工
	@RequestMapping("/emp/byId")
	public String queryEmpById(@RequestParam(value="empno",required=false)String empno){
		
		return "";
	}
	
	//添加员工 ,跳转到增加页面
	@RequestMapping("/emp/toAdd")
	public String toAdd(@ModelAttribute("emp")Emp emp,HttpSession session){
		loadData(session);
		return "AddEmp";
	}
	
	//添加员工
		@RequestMapping(value="/emp",method=RequestMethod.POST)
		public String addEmp(Emp emp){
			String empno = UUID.randomUUID().toString();
			emp.setEmpno(empno);
			empService.addEmp(emp);		
			return "redirect:/emp/conditionList";
		}
	
		//跳转到修改页面
		@RequestMapping("/emp/toUpdate")
		public String toUpdateEmp(@RequestParam("empno")String empno,
				 @ModelAttribute("emp") Emp emp,
				 Model model,HttpSession session){
			loadData(session);
		    emp = empService.queryEmpById(empno);
			model.addAttribute("emp", emp);
			return "UpdateEmp";
		}
			
		//修改员工
		@RequestMapping(value="/emp",method=RequestMethod.PUT)
		public String updateEmp(@ModelAttribute("emp")Emp emp,HttpSession session){
			//更新修改信息到数据库中
			 empService.updateEmp(emp);
			 //从session中取出pageBean和查询条件
			 PageBean<Emp> pageBean=(PageBean<Emp>) session.getAttribute("pageBean");
			 String  cd = (String) session.getAttribute("cd");
			 //更新pageBean的list
			 pageBean=empService.queryByCondition(pageBean.getPageNo(),
					 pageBean.getPageSize(),cd);
			 //将PageBean对象重新放回到session中
			 session.setAttribute("pageBean", pageBean);
			 return "redirect:/emp/reList";
		}
		
		@RequestMapping("/emp/reList")
		public String toEmpList(){
			return "ListEmp2";
		}
		
		
		//删除员工
		@RequestMapping(value="/emp/{empno}",method=RequestMethod.DELETE)
		public String delete(@PathVariable("empno") String empno){
			   empService.deleteEmp(empno);
			   return "redirect:/emp/conditionList";
		}
	
}



