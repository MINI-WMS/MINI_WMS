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

import com.ltsznh.modules.sal.entity.SalCustomerEntity;
import com.ltsznh.modules.sal.service.SalCustomerService;


/**
 * 客户管理
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:26
 */
@RestController
@RequestMapping("salcustomer")
public class SalCustomerController  extends AbstractController {
	@Autowired
	private SalCustomerService salCustomerService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("salcustomer:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SalCustomerEntity> salCustomerList = salCustomerService.queryList(query);
		int total = salCustomerService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(salCustomerList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{customerCode}")
	@RequiresPermissions("salcustomer:info")
	public R info(@PathVariable("customerCode") String customerCode){
		SalCustomerEntity salCustomer = salCustomerService.queryObject(customerCode);

		return R.ok().put("salCustomer", salCustomer);
	}

	/**
	 * 保存
	 */
	@SysLog("保存客户管理")
	@RequestMapping("/save")
	@RequiresPermissions("salcustomer:save")
	public R save(@RequestBody SalCustomerEntity salCustomer){
		salCustomer.setCreatorId(getUserId());
		salCustomer.setCreateDate(new Date());

		salCustomerService.save(salCustomer);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改客户管理")
	@RequestMapping("/update")
	@RequiresPermissions("salcustomer:update")
	public R update(@RequestBody SalCustomerEntity salCustomer){
		salCustomer.setModifierId(getUserId());
		salCustomer.setModifyDate(new Date());

		salCustomerService.update(salCustomer);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除客户管理")
	@RequestMapping("/delete")
	@RequiresPermissions("salcustomer:delete")
	public R delete(@RequestBody String[] customerCodes){
		salCustomerService.deleteBatch(customerCodes,getUserId());

		return R.ok();
	}

}
