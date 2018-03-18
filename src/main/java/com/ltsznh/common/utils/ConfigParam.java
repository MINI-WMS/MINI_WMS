package com.ltsznh.common.utils;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 系统参数相关Key
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-03-26 10:33
 */
public class ConfigParam {

	private final static String TOMCAT_PATH = System.getProperty("catalina.home");//Tomcat目录
	private final static String APP_DATA_DIR = "app_data";//数据跟目录

	private static String APP_NAME = "";//部署项目名称

	private static String DOWNLOAD_PATH = "";
	private static String UPLOAD_PATH = "";
	private static String DOWNLOAD_EXCEL_PATH = "";
	private static String UPLOAD_EXCEL_PATH = "";
	private static String TEMPLATE_EXCEL_PATH = "";
	private static String TEMPLATE_HTML_PATH = "";

	public static String getTomcatPath() {
		return TOMCAT_PATH;
	}

	public static String getAppDataDir() {
		return APP_DATA_DIR;
	}

	public static String getAppName() {
		// TODO Auto-generated method stub
		if (APP_NAME != null && !APP_NAME.equals("")) return APP_NAME;
//        String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String classPath = ConfigParam.class.getResource("/").getPath();
		File file = new File(classPath);
		file = file.getParentFile().getParentFile();
		APP_NAME = file.getName();
//        LoggerFactory.getLogger(getClass()).info("部署应用名称："+ APP_NAME);
		return APP_NAME;
	}

	public static String getDownloadPath() {
		if (DOWNLOAD_PATH != null && !DOWNLOAD_PATH.equals("")) return DOWNLOAD_PATH;
		DOWNLOAD_PATH = getTomcatPath() + File.separator + getAppDataDir() + File.separator + getAppName() + File.separator + "download";
		return DOWNLOAD_PATH;
	}

    public static String getUploadPath() {
        if (UPLOAD_PATH != null && !UPLOAD_PATH.equals("")) return UPLOAD_PATH;
        UPLOAD_PATH = getTomcatPath() + File.separator + getAppDataDir() + File.separator + getAppName() + File.separator + "upload";
        return UPLOAD_PATH;
    }

	public static String getDownloadExcelPath() {
		if (DOWNLOAD_EXCEL_PATH != null && !DOWNLOAD_EXCEL_PATH.equals("")) return DOWNLOAD_EXCEL_PATH;
		DOWNLOAD_EXCEL_PATH = getDownloadPath() + File.separator + "excel";
		return DOWNLOAD_EXCEL_PATH;
	}

	public static String getExcelTemplatePath() {
		if (TEMPLATE_EXCEL_PATH != null && !TEMPLATE_EXCEL_PATH.equals("")) return TEMPLATE_EXCEL_PATH;
		TEMPLATE_EXCEL_PATH = getTomcatPath() + File.separator + getAppDataDir() + File.separator + getAppName() + File.separator + "template"+ File.separator + "excel";
		return TEMPLATE_EXCEL_PATH;
	}

    public static String getUploadExcelPath() {
        if (UPLOAD_EXCEL_PATH != null && !UPLOAD_EXCEL_PATH.equals("")) return UPLOAD_EXCEL_PATH;
        UPLOAD_EXCEL_PATH = getUploadPath() + File.separator + "excel";
        return UPLOAD_EXCEL_PATH;
    }
}
