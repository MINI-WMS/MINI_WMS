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

import com.ltsznh.modules.wms.entity.WmsWarehouseEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseService;


/**
 * 仓库
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
@RestController
@RequestMapping("wmswarehouse")
public class WmsWarehouseController  extends AbstractController {
	@Autowired
	private WmsWarehouseService wmsWarehouseService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmswarehouse:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsWarehouseEntity> wmsWarehouseList = wmsWarehouseService.queryList(query);
		int total = wmsWarehouseService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsWarehouseList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{warehouseCode}")
	@RequiresPermissions("wmswarehouse:info")
	public R info(@PathVariable("warehouseCode") String warehouseCode){
		WmsWarehouseEntity wmsWarehouse = wmsWarehouseService.queryObject(warehouseCode);

		return R.ok().put("wmsWarehouse", wmsWarehouse);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库")
	@RequestMapping("/save")
	@RequiresPermissions("wmswarehouse:save")
	public R save(@RequestBody WmsWarehouseEntity wmsWarehouse){
		wmsWarehouse.setCreatorId(getUserId());
		wmsWarehouse.setCreateDate(new Date());

		wmsWarehouseService.save(wmsWarehouse);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改仓库")
	@RequestMapping("/update")
	@RequiresPermissions("wmswarehouse:update")
	public R update(@RequestBody WmsWarehouseEntity wmsWarehouse){
		wmsWarehouse.setModifierId(getUserId());
		wmsWarehouse.setModifyDate(new Date());

		wmsWarehouseService.update(wmsWarehouse);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除仓库")
	@RequestMapping("/delete")
	@RequiresPermissions("wmswarehouse:delete")
	public R delete(@RequestBody String[] warehouseCodes){
		wmsWarehouseService.deleteBatch(warehouseCodes,getUserId());

		return R.ok();
	}

}
