package com.ltsznh.common.utils;

import com.ltsznh.common.exception.FamilyException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Excel操作
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class ExcelUtils {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Workbook workbook;
	private CellStyle titleCellStyle;//设置标题行单元格格式
	private CellStyle dataCellStyle;// 设置数据内容单元格格式

	public ExcelUtils(SXSSFWorkbook workbook) {
		this.workbook = workbook;
		getTitleCellStyle();
		getDataCellStyle();
	}

	public Workbook generateHSSFWorkbook(List<Map<String, Object>> list, String title) {
		try {
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			SXSSFSheet sheet = workbook.createSheet();
//			sheet.autoSizeColumn(1, true);//自适应列宽度

//		//样式设置
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFillForegroundColor(XSSFColor.toXSSFColor(Color.));
//		style.setFillPattern(style..SOLID_FOREGROUND);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		// 生成一个字体
//		HSSFFont font = book.createFont();
//		font.setColor(HSSFColor.VIOLET.index);
//		font.setFontHeightInPoints((short) 12);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//
//
//		HSSFCellStyle style2 = book.createCellStyle();
//		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		//设置上下左右边框
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//
//		//填充表头标题
//		int colSize = list.get(0).entrySet().size();
//		System.out.println("size:" + colSize);
//		//合并单元格供标题使用(表名)
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSize-1));
//		HSSFRow firstRow = sheet.createRow(0);//第几行（从0开始）
//		HSSFCell firstCell = firstRow.createCell(0);
//		firstCell.setCellValue(title);
//		firstCell.setCellStyle(style);

			//填充表头header
			SXSSFRow row = sheet.createRow(1);
			SXSSFCell cell = row.createCell(0);
			cell.setCellValue("test" + list.get(0).get(""));
//	Object aa = list.get(0);


//		Map<String,String> map = (HashMap<String, String>)
//				String test = map.get("isEnabled");
//		Set<Map.Entry<String, String>> set = list.get(0).entrySet();
//		List<Map.Entry<String, String>> l = new ArrayList<Map.Entry<String, String>>(set);
//		System.out.println("l:" + l.size());
//		for (int i = 0; i < l.size(); i++) {
//			String key = l.get(i).getKey();
//			System.out.println(key);
//			XSSFCell cell = row.createCell(i);
//			cell.setCellValue(key);
//			cell.setCellStyle(style2);
//		}

			//填充表格内容
//		System.out.println("list:" + list.size());
//		for (int i = 0; i < list.size(); i++) {
//			XSSFRow row2 = sheet.createRow(i + 2);//index：第几行
//			Map<String, String> map = list.get(i);
//			Set<Map.Entry<String, String>> set2 = map.entrySet();
//			List<Map.Entry<String, String>> ll = new ArrayList(set2);
//			for (int j = 0; j < ll.size(); j++) {
//				String val = ll.get(j).getValue();
//				XSSFCell cell = row2.createCell(j);//第几列：从0开始
//				cell.setCellValue(val);
////				cell.setCellStyle(style2);
//			}
//		}
			File file = new File("d:/sxssf.xlsx");
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.flush();
			out.close();

			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//	public void main(String[] args) {
//		ExcelUtils excelUtils = new ExcelUtils();
//
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		Map<String, String> item = new HashMap<String, String>();
//		item.put("test", "testvalue");
//
//		list.add(item);
//
//		Workbook xssfWorkbook = excelUtils.generateHSSFWorkbook(list, "test");
//
//		File desFile = new File("d:\\人员表.xls");
//		try {
//			FileOutputStream fos = new FileOutputStream(desFile);
//
//			xssfWorkbook.write(fos);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public CellStyle getDataCellStyle() {
		return getDataCellStyle(workbook);
	}

	public CellStyle getDataCellStyle(Workbook workbook) {
		dataCellStyle
				= workbook.createCellStyle();//设置数据内容单元格格式

//		cellStyle.setVerticalAlignment();// 垂直居中
//		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
//		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
//		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
//		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		titleCellStyle.setWrapText(true);

		Font cellFont = workbook.createFont();
		cellFont.setFontHeightInPoints((short) 10);

		titleCellStyle.setFont(cellFont);
		return titleCellStyle;
	}

	public CellStyle getTitleCellStyle() {
		return getTitleCellStyle(workbook);
	}

	public CellStyle getTitleCellStyle(Workbook workbook) {
		titleCellStyle = workbook.createCellStyle();//设置标题行单元格格式

//		cellStyle.setVerticalAlignment();// 垂直居中
//		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
//		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
//		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
//		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		titleCellStyle.setWrapText(true);


		Font cellFont = workbook.createFont();
		cellFont.setFontHeightInPoints((short) 11);
//		cellFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		titleCellStyle.setFont(cellFont);
		return titleCellStyle;
	}

	public void autoSetColumnWidth(Sheet sheet, SXSSFCell cell) {
		try {
			String str = cell.getStringCellValue();
			if (str != null) {
				// 自适应中英文列宽
				int strLength = str.getBytes("GBK").length > 50 ? 50 : str.getBytes("GBK").length;
				int with;
				if (cell.getRowIndex() == 0) {
					with = strLength * 256;
					cell.setCellStyle(dataCellStyle);
				} else {
					with = strLength * 250;
					cell.setCellStyle(titleCellStyle);
				}
				if (with > sheet.getColumnWidth(cell.getColumnIndex()))
					sheet.setColumnWidth(cell.getColumnIndex(), with);
			}
		} catch (FamilyException e) {
			logger.error(this.getClass().toString(), e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(this.getClass().toString(), e.getMessage());
		}


	}
}
