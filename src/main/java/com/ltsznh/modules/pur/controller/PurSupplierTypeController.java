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

import com.ltsznh.modules.pur.entity.PurSupplierTypeEntity;
import com.ltsznh.modules.pur.service.PurSupplierTypeService;


/**
 * 供应商类型
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
@RestController
@RequestMapping("pursuppliertype")
public class PurSupplierTypeController  extends AbstractController {
	@Autowired
	private PurSupplierTypeService purSupplierTypeService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pursuppliertype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PurSupplierTypeEntity> purSupplierTypeList = purSupplierTypeService.queryList(query);
		int total = purSupplierTypeService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(purSupplierTypeList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{supplierTypeCode}")
	@RequiresPermissions("pursuppliertype:info")
	public R info(@PathVariable("supplierTypeCode") String supplierTypeCode){
		PurSupplierTypeEntity purSupplierType = purSupplierTypeService.queryObject(supplierTypeCode);

		return R.ok().put("purSupplierType", purSupplierType);
	}

	/**
	 * 保存
	 */
	@SysLog("保存供应商类型")
	@RequestMapping("/save")
	@RequiresPermissions("pursuppliertype:save")
	public R save(@RequestBody PurSupplierTypeEntity purSupplierType){
		purSupplierType.setCreatorId(getUserId());
		purSupplierType.setCreateDate(new Date());

		purSupplierTypeService.save(purSupplierType);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改供应商类型")
	@RequestMapping("/update")
	@RequiresPermissions("pursuppliertype:update")
	public R update(@RequestBody PurSupplierTypeEntity purSupplierType){
		purSupplierType.setModifierId(getUserId());
		purSupplierType.setModifyDate(new Date());

		purSupplierTypeService.update(purSupplierType);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除供应商类型")
	@RequestMapping("/delete")
	@RequiresPermissions("pursuppliertype:delete")
	public R delete(@RequestBody String[] supplierTypeCodes){
		purSupplierTypeService.deleteBatch(supplierTypeCodes,getUserId());

		return R.ok();
	}

}
