package com.ltsznh.common.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ExportExcel {

    //下载Excel文件
	public void Export(String filename, Workbook book, HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
			OutputStream ouputStream = null;

			ouputStream = response.getOutputStream();

			book.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}


	public String exportToFile(SXSSFWorkbook workbook, String fileName, String suffix) throws IOException {
		if (!suffix.startsWith(".")) suffix = "." + suffix;//后缀补全

		File file = new File(ConfigParam.getDownloadExcelPath() + File.separator + fileName + UUID.randomUUID()+ suffix);
		if(!file.getParentFile().exists())file.getParentFile().mkdirs();
		if(file.exists())file.delete();
		file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.flush();
		out.close();

		return file.getName();
	}

	public String exportToFile(SXSSFWorkbook workbook, String fileName) throws IOException {
		return exportToFile(workbook, fileName, "xlsx");
	}

	public String exportToFile(SXSSFWorkbook workbook) throws IOException {
		return exportToFile(workbook,  UUID.randomUUID() + "", "xlsx");
	}


}
