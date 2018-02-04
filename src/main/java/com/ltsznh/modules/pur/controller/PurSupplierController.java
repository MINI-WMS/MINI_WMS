package com.ltsznh.modules.pur.controller;

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

import com.ltsznh.modules.pur.entity.PurSupplierEntity;
import com.ltsznh.modules.pur.service.PurSupplierService;


/**
 * 供应商
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
@RestController
@RequestMapping("pursupplier")
public class PurSupplierController  extends AbstractController {
	@Autowired
	private PurSupplierService purSupplierService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pursupplier:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PurSupplierEntity> purSupplierList = purSupplierService.queryList(query);
		int total = purSupplierService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(purSupplierList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * select2的建议数据
	 */
	@RequestMapping("/select2")
	public R select2(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<PurSupplierEntity> purSupplierList = purSupplierService.querySelect2(query);
//		int total = purSupplierService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(purSupplierList, 0, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{supplierCode}")
	@RequiresPermissions("pursupplier:info")
	public R info(@PathVariable("supplierCode") String supplierCode){
		PurSupplierEntity purSupplier = purSupplierService.queryObject(supplierCode);

		return R.ok().put("purSupplier", purSupplier);
	}

	/**
	 * 保存
	 */
	@SysLog("保存供应商")
	@RequestMapping("/save")
	@RequiresPermissions("pursupplier:save")
	public R save(@RequestBody PurSupplierEntity purSupplier){
		purSupplier.setCreatorId(getUserId());
		purSupplier.setCreateDate(new Date());

		purSupplierService.save(purSupplier);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改供应商")
	@RequestMapping("/update")
	@RequiresPermissions("pursupplier:update")
	public R update(@RequestBody PurSupplierEntity purSupplier){
		purSupplier.setModifierId(getUserId());
		purSupplier.setModifyDate(new Date());

		purSupplierService.update(purSupplier);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除供应商")
	@RequestMapping("/delete")
	@RequiresPermissions("pursupplier:delete")
	public R delete(@RequestBody String[] supplierCodes){
		purSupplierService.deleteBatch(supplierCodes,getUserId());

		return R.ok();
	}

}
