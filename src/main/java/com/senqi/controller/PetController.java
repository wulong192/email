package com.senqi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.senqi.entity.Pet;
import com.senqi.service.IPetService;
import com.senqi.util.ExcelUtil;

@Controller
public class PetController {

	@Autowired
	private IPetService is;

	/**
	 * ����excel
	 * @throws Exception 
	 */
	@RequestMapping("down")
	public ResponseEntity<byte[]> down(HttpServletRequest req, Integer start, Integer end) throws Exception {

		String path = req.getServletContext().getRealPath("upload");
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		// Ҫ���ص�Ŀ���ļ�
		File target = new File(path, "��ǰ����.xlsx");
		
		
		// ���ݴ�����start, end ��ѯlist
		List<Pet> list = is.getList(start, end);
		
		
		// ��list���뵽excel�ļ���
		ExcelUtil.parseList(target, list);
		
		
		HttpHeaders headers = new HttpHeaders();
		// ����ͷ��Ϣ
		// attachment: �Ը�������ʽ���أ�Ĭ�ϵ�ֵinline�� ֱ����������д�
		
		// ͨ��������utf-�����Ļ�ȡΪbyte[]����
		// Ȼ��ʹ��iso-8859-1 �ı����������д���������鵽�ͻ���
		String fileName = new String("��ǰ����.xlsx".getBytes("utf-8"), "iso-8859-1");
		
		headers.setContentDispositionFormData("attachment", fileName);

		ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(target), headers,
				HttpStatus.OK);
		
		return re;
	}

	/**
	 * �ϴ�excel, �����������ݿ�
	 * 
	 * @param file
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/up")
	public String up(MultipartFile file, HttpServletRequest req, Model m) throws Exception {

		String msg = "�ϴ�����ʧ�ܣ����Ժ�����";
		try {
			String path = req.getServletContext().getRealPath("upload");

			File temp = new File(path);
			// ���Ŀ¼�����ڣ��ʹ���Ŀ¼
			if (!temp.exists()) {
				temp.mkdirs();
			}

			// ʱ��� + ����� +
			// UUID
			// aa.xlsx bb.xls
			String fileName = file.getOriginalFilename();
			// �ļ���׺
			String ext = FilenameUtils.getExtension(fileName);
			String str = UUID.randomUUID().toString();
			fileName = str + "." + ext;

			// �ϴ���Ŀ���ļ�
			File target = new File(path, fileName);

			// �ϴ�����
			file.transferTo(target);

			// �����ϴ���excle
			List<Pet> list = ExcelUtil.parseFile(target);

			// ����service�㣬��������
			is.save(list);

			msg = "�ϴ������ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("msg", msg);

		return "msg";
	}

}
