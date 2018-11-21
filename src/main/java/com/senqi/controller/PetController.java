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
	 * 下载excel
	 * @throws Exception 
	 */
	@RequestMapping("down")
	public ResponseEntity<byte[]> down(HttpServletRequest req, Integer start, Integer end) throws Exception {

		String path = req.getServletContext().getRealPath("upload");
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		// 要下载的目标文件
		File target = new File(path, "当前数据.xlsx");
		
		
		// 根据传来的start, end 查询list
		List<Pet> list = is.getList(start, end);
		
		
		// 将list存入到excel文件中
		ExcelUtil.parseList(target, list);
		
		
		HttpHeaders headers = new HttpHeaders();
		// 设置头信息
		// attachment: 以附件的形式下载，默认的值inline， 直接在浏览器中打开
		
		// 通过本来的utf-将中文获取为byte[]数组
		// 然后使用iso-8859-1 的编码在网络中传输这个数组到客户端
		String fileName = new String("当前数据.xlsx".getBytes("utf-8"), "iso-8859-1");
		
		headers.setContentDispositionFormData("attachment", fileName);

		ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(target), headers,
				HttpStatus.OK);
		
		return re;
	}

	/**
	 * 上传excel, 并解析到数据库
	 * 
	 * @param file
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/up")
	public String up(MultipartFile file, HttpServletRequest req, Model m) throws Exception {

		String msg = "上传解析失败，请稍后重试";
		try {
			String path = req.getServletContext().getRealPath("upload");

			File temp = new File(path);
			// 如果目录不存在，就创建目录
			if (!temp.exists()) {
				temp.mkdirs();
			}

			// 时间戳 + 随机数 +
			// UUID
			// aa.xlsx bb.xls
			String fileName = file.getOriginalFilename();
			// 文件后缀
			String ext = FilenameUtils.getExtension(fileName);
			String str = UUID.randomUUID().toString();
			fileName = str + "." + ext;

			// 上传的目标文件
			File target = new File(path, fileName);

			// 上传方法
			file.transferTo(target);

			// 解析上传的excle
			List<Pet> list = ExcelUtil.parseFile(target);

			// 调用service层，存入数据
			is.save(list);

			msg = "上传解析成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}

		m.addAttribute("msg", msg);

		return "msg";
	}

}
