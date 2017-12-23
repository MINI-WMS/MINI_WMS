package com.ltsznh.modules.wms.controller;

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

import com.ltsznh.modules.wms.entity.WmsWarehouseInventoryEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseInventoryService;


/**
 * 仓库库存
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:55
 */
@RestController
@RequestMapping("wmswarehouseinventory")
public class WmsWarehouseInventoryController  extends AbstractController {
	@Autowired
	private WmsWarehouseInventoryService wmsWarehouseInventoryService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmswarehouseinventory:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsWarehouseInventoryEntity> wmsWarehouseInventoryList = wmsWarehouseInventoryService.queryList(query);
		int total = wmsWarehouseInventoryService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsWarehouseInventoryList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{wiId}")
	@RequiresPermissions("wmswarehouseinventory:info")
	public R info(@PathVariable("wiId") Long wiId){
		WmsWarehouseInventoryEntity wmsWarehouseInventory = wmsWarehouseInventoryService.queryObject(wiId);

		return R.ok().put("wmsWarehouseInventory", wmsWarehouseInventory);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库库存")
	@RequestMapping("/save")
	@RequiresPermissions("wmswarehouseinventory:save")
	public R save(@RequestBody WmsWarehouseInventoryEntity wmsWarehouseInventory){
		wmsWarehouseInventory.setCreatorId(getUserId());
		wmsWarehouseInventory.setCreateDate(new Date());

		wmsWarehouseInventoryService.save(wmsWarehouseInventory);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改仓库库存")
	@RequestMapping("/update")
	@RequiresPermissions("wmswarehouseinventory:update")
	public R update(@RequestBody WmsWarehouseInventoryEntity wmsWarehouseInventory){
		wmsWarehouseInventory.setModifierId(getUserId());
		wmsWarehouseInventory.setModifyDate(new Date());

		wmsWarehouseInventoryService.update(wmsWarehouseInventory);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除仓库库存")
	@RequestMapping("/delete")
	@RequiresPermissions("wmswarehouseinventory:delete")
	public R delete(@RequestBody Long[] wiIds){
		wmsWarehouseInventoryService.deleteBatch(wiIds,getUserId());

		return R.ok();
	}

}
