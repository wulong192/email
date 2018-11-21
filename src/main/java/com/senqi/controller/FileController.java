package com.senqi.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	
	@RequestMapping("/upfile")
	public String upfile(MultipartFile file, HttpServletRequest req, Model m) {
		String msg = "�ϴ�ʧ�ܣ������ԣ�";
		try {
			String path = req.getServletContext().getRealPath("upload");
			File pathFile = new File(path);
			if(!pathFile.exists()) {
				pathFile.mkdirs();
			}
			String fileName = file.getOriginalFilename();
			
			File targetFile = new File(path, fileName);
			
			file.transferTo(targetFile);
			
			msg = "�ϴ��ɹ�, �鿴·��Ϊ��" + path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("msg", msg);
		
		return "msg";
	}
	
	
}
