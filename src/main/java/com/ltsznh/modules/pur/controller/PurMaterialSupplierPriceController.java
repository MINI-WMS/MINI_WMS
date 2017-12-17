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

import com.ltsznh.modules.pur.entity.PurMaterialSupplierPriceEntity;
import com.ltsznh.modules.pur.service.PurMaterialSupplierPriceService;


/**
 * 物料供应商价格
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
@RestController
@RequestMapping("purmaterialsupplierprice")
public class PurMaterialSupplierPriceController  extends AbstractController {
	@Autowired
	private PurMaterialSupplierPriceService purMaterialSupplierPriceService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("purmaterialsupplierprice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PurMaterialSupplierPriceEntity> purMaterialSupplierPriceList = purMaterialSupplierPriceService.queryList(query);
		int total = purMaterialSupplierPriceService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(purMaterialSupplierPriceList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{mspId}")
	@RequiresPermissions("purmaterialsupplierprice:info")
	public R info(@PathVariable("mspId") Long mspId){
		PurMaterialSupplierPriceEntity purMaterialSupplierPrice = purMaterialSupplierPriceService.queryObject(mspId);

		return R.ok().put("purMaterialSupplierPrice", purMaterialSupplierPrice);
	}

	/**
	 * 保存
	 */
	@SysLog("保存物料供应商价格")
	@RequestMapping("/save")
	@RequiresPermissions("purmaterialsupplierprice:save")
	public R save(@RequestBody PurMaterialSupplierPriceEntity purMaterialSupplierPrice){
		purMaterialSupplierPrice.setCreatorId(getUserId());
		purMaterialSupplierPrice.setCreateDate(new Date());

		purMaterialSupplierPriceService.save(purMaterialSupplierPrice);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改物料供应商价格")
	@RequestMapping("/update")
	@RequiresPermissions("purmaterialsupplierprice:update")
	public R update(@RequestBody PurMaterialSupplierPriceEntity purMaterialSupplierPrice){
		purMaterialSupplierPrice.setModifierId(getUserId());
		purMaterialSupplierPrice.setModifyDate(new Date());

		purMaterialSupplierPriceService.update(purMaterialSupplierPrice);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除物料供应商价格")
	@RequestMapping("/delete")
	@RequiresPermissions("purmaterialsupplierprice:delete")
	public R delete(@RequestBody Long[] mspIds){
		purMaterialSupplierPriceService.deleteBatch(mspIds,getUserId());

		return R.ok();
	}

}
