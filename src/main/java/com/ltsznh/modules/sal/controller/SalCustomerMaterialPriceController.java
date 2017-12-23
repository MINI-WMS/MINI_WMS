package com.ltsznh.modules.sal.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;

import com.ltsznh.modules.sal.entity.SalCustomerMaterialPriceEntity;
import com.ltsznh.modules.sal.service.SalCustomerMaterialPriceService;


/**
 * 客户物料价格
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:26
 */
@RestController
@RequestMapping("salcustomermaterialprice")
public class SalCustomerMaterialPriceController  extends AbstractController {
	@Autowired
	private SalCustomerMaterialPriceService salCustomerMaterialPriceService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("salcustomermaterialprice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SalCustomerMaterialPriceEntity> salCustomerMaterialPriceList = salCustomerMaterialPriceService.queryList(query);
		int total = salCustomerMaterialPriceService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(salCustomerMaterialPriceList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{mcpId}")
	@RequiresPermissions("salcustomermaterialprice:info")
	public R info(@PathVariable("mcpId") Long mcpId){
		SalCustomerMaterialPriceEntity salCustomerMaterialPrice = salCustomerMaterialPriceService.queryObject(mcpId);

		return R.ok().put("salCustomerMaterialPrice", salCustomerMaterialPrice);
	}

	/**
	 * 保存
	 */
	@SysLog("保存客户物料价格")
	@RequestMapping("/save")
	@RequiresPermissions("salcustomermaterialprice:save")
	public R save(@RequestBody SalCustomerMaterialPriceEntity salCustomerMaterialPrice){
		salCustomerMaterialPrice.setCreatorId(getUserId());
		salCustomerMaterialPrice.setCreateDate(new Date());

		salCustomerMaterialPriceService.save(salCustomerMaterialPrice);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改客户物料价格")
	@RequestMapping("/update")
	@RequiresPermissions("salcustomermaterialprice:update")
	public R update(@RequestBody SalCustomerMaterialPriceEntity salCustomerMaterialPrice){
		salCustomerMaterialPrice.setModifierId(getUserId());
		salCustomerMaterialPrice.setModifyDate(new Date());

		salCustomerMaterialPriceService.update(salCustomerMaterialPrice);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除客户物料价格")
	@RequestMapping("/delete")
	@RequiresPermissions("salcustomermaterialprice:delete")
	public R delete(@RequestBody Long[] mcpIds){
		salCustomerMaterialPriceService.deleteBatch(mcpIds,getUserId());

		return R.ok();
	}

}
