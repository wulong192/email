package com.senqi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpController {

	
	@RequestMapping("/http")
	public String http(String userName, HttpServletRequest req) throws Exception {
		
		String un = req.getParameter("userName");
		
		System.out.println(userName);
		
		System.out.println("ʹ��req���յ���ֵ��" + un);
		
		// ��̨���֪��������get  ����  post�ύ��
		String method = req.getMethod();
		
		System.out.println("����ʽ�ǣ�" + method);
		
		
		return "msg";
	}
	
	

}
