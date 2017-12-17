package com.ltsznh.modules.sys.controller;


import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.common.exception.FamilyException;
import com.ltsznh.common.utils.*;
import com.ltsznh.modules.sys.entity.SysConfigEntity;
import com.ltsznh.modules.sys.redis.SysConfigRedis;
import com.ltsznh.modules.sys.service.SysConfigService;
import com.ltsznh.common.validator.ValidatorUtils;
import org.apache.commons.beanutils.BeanMap;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 系统配置信息
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private SysConfigRedis sysConfigRedis;

	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<SysConfigEntity> configList = sysConfigService.queryList(query);
		int total = sysConfigService.queryTotal(query);
		List<Map<String, Object>> mapList = null;

		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
		pageUtil.setColumnWidth(configList);

		return R.ok().put("page", pageUtil);

	}

	/**
	 * 导出所有配置列表
	 */
	@RequestMapping("/excel")
	@RequiresPermissions("sys:config:list")
	public R excel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
		//查询并导出列表数据
		Query query = new Query(params);
		int page = 1, rows = 10000;
		query.setPage(page);//默认从第一页开始导出
		query.setLimit(rows);
		//设置excel导出最大行数限制
		int total = sysConfigService.queryTotal(query);

		int excelRowsLimit = 10000;
		try {
			excelRowsLimit = Integer.parseInt(sysConfigRedis.get("download_excel_rows_limit").getConfigValue().toString());
		} catch (Exception e) {
			logger.error(this.getClass().toString(), e.getMessage());
		}
		rows = total > excelRowsLimit ? excelRowsLimit : total; //一次取的行数
		query.setLimit(rows);
		List<Map<String, String>> excelList = new ArrayList<>();

		SXSSFWorkbook workbook = new SXSSFWorkbook();//电子表格
		SXSSFSheet sheet = workbook.createSheet();//工作簿
		SXSSFRow row;//行
		SXSSFCell cell;

		ExcelUtils excelUtils = new ExcelUtils(workbook);

		page = 0;
		do {
			page++;
			query.setPage(page);//默认从第一页开始导出
			List<SysConfigEntity> configList = sysConfigService.queryList(query);
			try {
				for (int i = (int) query.get("offset"); i < configList.size(); i++) {
					row = sheet.createRow(i + 1);

					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(configList.get(i).getConfigKey());
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue(configList.get(i).getConfigValue());
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(configList.get(i).getRemark());
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(configList.get(i).getModifierName());
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(DateUtils.formatToDateTime(configList.get(i).getModifyDate()));
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(configList.get(i).getCreatorName());
					excelUtils.autoSetColumnWidth(sheet, cell);

					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue(DateUtils.formatToDateTime(configList.get(i).getCreateDate()));
					excelUtils.autoSetColumnWidth(sheet, cell);
				}
			} catch (Exception e) {
				logger.error(this.getClass().toString(), e);
				return R.error(e.getMessage());
			}

		} while (page * rows < total);
		try {
			//标题行
			String[] titles = new String[]{"参数", "参数值", "备注", "修改用户", "修改时间", "创建用户", "创建时间"};
			row = sheet.createRow(0);
			for (int i = 0; i < titles.length; i++) {
				cell = row.createCell(i, CellType.STRING);
				cell.setCellValue(titles[i]);
				excelUtils.autoSetColumnWidth(sheet, cell);
			}
			sheet.createFreezePane(1, 2);//设置冻结列行

			String fileName = new ExportExcel().exportToFile(workbook, "系统参数配置");
			Map<String, String> map = new HashMap<String, String>();
			map.put("fileName", fileName);
			map.put("downloadFileName", fileName);
			excelList.add(map);

			return R.ok().put("excelList", excelList);
		} catch (FamilyException e) {
			logger.error(this.getClass().toString(), e);
			return R.error(e.getMessage());
		} catch (IOException e) {
			logger.error(this.getClass().toString(), e);
			return R.error(e.getMessage());
		}

	}


	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") Long id) {
		SysConfigEntity config = sysConfigService.queryObject(id);

		return R.ok().put("config", config);
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		config.setCreatorId(getUserId());
		config.setCreateDate(new Date());
		sysConfigService.save(config);

		return R.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		config.setModifierId(getUserId());
		config.setModifyDate(new Date());
		sysConfigService.update(config);

		return R.ok();
	}

	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody Long[] ids) {
		sysConfigService.deleteBatch(ids, getUserId());

		return R.ok();
	}

}
