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

import com.ltsznh.modules.sal.entity.SalCustomerTypeEntity;
import com.ltsznh.modules.sal.service.SalCustomerTypeService;


/**
 * 客户类型
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
@RestController
@RequestMapping("salcustomertype")
public class SalCustomerTypeController  extends AbstractController {
	@Autowired
	private SalCustomerTypeService salCustomerTypeService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("salcustomertype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SalCustomerTypeEntity> salCustomerTypeList = salCustomerTypeService.queryList(query);
		int total = salCustomerTypeService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(salCustomerTypeList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{customerTypeCode}")
	@RequiresPermissions("salcustomertype:info")
	public R info(@PathVariable("customerTypeCode") String customerTypeCode){
		SalCustomerTypeEntity salCustomerType = salCustomerTypeService.queryObject(customerTypeCode);

		return R.ok().put("salCustomerType", salCustomerType);
	}

	/**
	 * 保存
	 */
	@SysLog("保存客户类型")
	@RequestMapping("/save")
	@RequiresPermissions("salcustomertype:save")
	public R save(@RequestBody SalCustomerTypeEntity salCustomerType){
		salCustomerType.setCreatorId(getUserId());
		salCustomerType.setCreateDate(new Date());

		salCustomerTypeService.save(salCustomerType);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改客户类型")
	@RequestMapping("/update")
	@RequiresPermissions("salcustomertype:update")
	public R update(@RequestBody SalCustomerTypeEntity salCustomerType){
		salCustomerType.setModifierId(getUserId());
		salCustomerType.setModifyDate(new Date());

		salCustomerTypeService.update(salCustomerType);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除客户类型")
	@RequestMapping("/delete")
	@RequiresPermissions("salcustomertype:delete")
	public R delete(@RequestBody String[] customerTypeCodes){
		salCustomerTypeService.deleteBatch(customerTypeCodes,getUserId());

		return R.ok();
	}

}
