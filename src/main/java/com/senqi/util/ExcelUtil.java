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
	 * ��list����д��excel�ļ���
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void parseList(File file, List<Pet> list) throws Exception {

		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("������Ϣ��");

		for (int i = 0; i < list.size(); i++) {
			
			// id  petName   gender   age   address
			Row row = sheet.createRow(i);
			
			row.createCell(0).setCellValue(list.get(i).getId());
			row.createCell(1).setCellValue(list.get(i).getPetName());
			row.createCell(2).setCellValue(list.get(i).getGender());
			row.createCell(3).setCellValue(list.get(i).getAge());
			row.createCell(4).setCellValue(list.get(i).getAddress());
		}
		
		
		// �ѹ���������д��������˴���
		workbook.write(new FileOutputStream(file));
		
	}

	/**
	 * ��excel�����ݽ���ΪList����
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

			// ÿһ�ж�Ӧһ��������Ϣ
			Pet pet = new Pet();

			// ��ȡ��ÿһ��
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

			// ��pet����װ�뵽list��
			list.add(pet);
		}

		return list;
	}

}
