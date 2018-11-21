package com.senqi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.senqi.entity.Pet;

public class ExcelUtil {

	/**
	 * 将list对象写入excel文件中
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void parseList(File file, List<Pet> list) throws Exception {

		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("宠物信息表");

		for (int i = 0; i < list.size(); i++) {
			
			// id  petName   gender   age   address
			Row row = sheet.createRow(i);
			
			row.createCell(0).setCellValue(list.get(i).getId());
			row.createCell(1).setCellValue(list.get(i).getPetName());
			row.createCell(2).setCellValue(list.get(i).getGender());
			row.createCell(3).setCellValue(list.get(i).getAge());
			row.createCell(4).setCellValue(list.get(i).getAddress());
		}
		
		
		// 把工作薄对象写入服务器端磁盘
		workbook.write(new FileOutputStream(file));
		
	}

	/**
	 * 将excel的数据解析为List对象
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<Pet> parseFile(File file) throws Exception {

		List<Pet> list = new ArrayList<Pet>();

		Workbook workbook = WorkbookFactory.create(file);

		Sheet sheet = workbook.getSheet("Sheet1");

		int rownum = sheet.getPhysicalNumberOfRows();

		for (int j = 0; j < rownum; j++) {

			// 每一行对应一个宠物信息
			Pet pet = new Pet();

			// 获取到每一行
			Row row = sheet.getRow(j);
			int cellnum = row.getPhysicalNumberOfCells();

			for (int x = 0; x < cellnum; x++) {
				Cell cell = row.getCell(x);

				String str = "";
				Double num = null;
				if (cell.getCellTypeEnum() == CellType.STRING) {
					str = cell.getStringCellValue();
				} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					num = cell.getNumericCellValue();
				}

				if (x == 0) {
					pet.setId(num.intValue());
				} else if (x == 1) {
					pet.setPetName(str);
				} else if (x == 2) {
					pet.setGender(str);
				} else if (x == 3) {
					pet.setAge(num.intValue());
				} else if (x == 4) {
					pet.setAddress(str);
				}
			}

			// 将pet对象装入到list中
			list.add(pet);
		}

		return list;
	}

}
