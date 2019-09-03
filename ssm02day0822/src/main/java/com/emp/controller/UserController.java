package com.emp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.User;
import com.emp.service.UserService;

@Controller
public class UserController {
	//ע��ҵ������
	@Resource
	private UserService userService;
	
	//��ת��δ��Ȩ��ҳ��
	@RequestMapping("/user/unauthorized")
	public String toUnauthorized(){
		return "Unauthorized";
	}
	
	//��ת��ע��ҳ��
	@RequestMapping("/user/toRegister")
	public String toRegister(){
		return "Register";
	}
	
	//ע���û�
	@RequestMapping("user/register")
	public String register(User user){
		userService.addUser(user);
		//�ض��� ����¼ҳ��
		return "redirect:/user/toLogin";
	}
	
	//��ת����¼ҳ��
	@RequestMapping("/user/toLogin")
	public String toLogin(){
		return "Login";
	}
	
	
	@RequestMapping("/user/login")
	public String login(User user,
			@RequestParam(value="RememberMe",required=false,defaultValue="0")Integer RememberMe,
	        Model model){
		
		//��ȡ��ǰ�û� Subject ���� ���� "/user/login"����Ķ���(�û�,����)
		Subject subject=SecurityUtils.getSubject();
		//����һ�����ƶ���
		UsernamePasswordToken token= 
				new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//�����Ƿ�ʹ�ü�ס��
			if(RememberMe==1){
				//ʹ�ü�ס�ҹ���
				token.setRememberMe(true);
			}
			//Ϊ��ǰ�û�������֤����Ȩ
			subject.login(token);
			//�ض�����ҳԱ���б�
			return "redirect:/emp/conditionList";
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("msg", "�û������������");
			return "Login";
		}
	}
	
	
}
