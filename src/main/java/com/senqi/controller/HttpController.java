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
		
		System.out.println("使用req接收到的值：" + un);
		
		// 后台如何知道到底是get  还是  post提交？
		String method = req.getMethod();
		
		System.out.println("请求方式是：" + method);
		
		
		return "msg";
	}
	
	

}
