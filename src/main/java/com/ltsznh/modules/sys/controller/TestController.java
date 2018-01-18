package com.ltsznh.modules.sys.controller;


import com.ltsznh.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//import com.ltsznh.util.template.ExcelToHtml;

/**
 * 系统用户
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/test")
public class TestController extends AbstractController {


	/**
	 * 测试excel模板转html
	 */
	@RequestMapping("/excelTemplate")
	public R testExcel(@RequestParam Map<String, Object> params) {
//		ExcelToHtml excelToHtml = new ExcelToHtml();
//		String html = excelToHtml.getHtml(getExcelTemplatePath() + File.separator + "test_template.xlsx");
		String html = "";
		return R.ok().put("html", html);
	}

}
