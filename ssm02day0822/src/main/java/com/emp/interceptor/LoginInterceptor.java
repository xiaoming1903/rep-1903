package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//��¼������
/**
    preHandle��ҵ��������������֮ǰ������;
	postHandle��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ��;
	afterCompletion��DispatcherServlet��ȫ����������󱻵���,������������Դ��. 
	afterCompletion()ִ����ɺ�ʼ��Ⱦҳ��
	
	����:
	preHandle-->/emp/conditonList-->postHandle-->����ListEmp.jsp(html)
     -->afterCompletion --->��������ش�html

*/
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, 
			HttpServletResponse response, Object handle, Exception exception)
			throws Exception {
		request.setAttribute("msg", "���ȵ�¼2");
		
	}

	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handle, ModelAndView modelAndView)
			throws Exception {
		request.setAttribute("msg", "���ȵ�¼3");
		
	}

	@Override
	/**
	 * return true ������ ����,����ҵ�����,����/emp/conditionList
	 * return false ����,������ҵ�����
	 */
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handle) throws Exception {
		Object user = request.getSession().getAttribute("user");
		if(user!=null){
			//�Ѿ���¼��
			return true;
		}
		//ת����Login,jsp
		//�󶨴�����Ϣ ,
		request.setAttribute("msg", "���ȵ�¼!");
		String appName = request.getServletContext().getContextPath();
		System.out.println(appName);
		//ת��������������
		request.getRequestDispatcher("/user/toLogin")
		.forward(request, response);
		return false;
	}

}


