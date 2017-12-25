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

import com.ltsznh.modules.wms.entity.WmsTransferOrderEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderService;


/**
 * 仓库入出库单
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:03
 */
@RestController
@RequestMapping("wmstransferorder")
public class WmsTransferOrderController  extends AbstractController {
	@Autowired
	private WmsTransferOrderService wmsTransferOrderService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderEntity> wmsTransferOrderList = wmsTransferOrderService.queryList(query);
		int total = wmsTransferOrderService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{warehouseCode}")
	@RequiresPermissions("wmstransferorder:info")
	public R info(@PathVariable("warehouseCode") String warehouseCode){
		WmsTransferOrderEntity wmsTransferOrder = wmsTransferOrderService.queryObject(warehouseCode);

		return R.ok().put("wmsTransferOrder", wmsTransferOrder);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库入出库单")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorder:save")
	public R save(@RequestBody WmsTransferOrderEntity wmsTransferOrder){
		wmsTransferOrder.setCreatorId(getUserId());
		wmsTransferOrder.setCreateDate(new Date());

		wmsTransferOrderService.save(wmsTransferOrder);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改仓库入出库单")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferorder:update")
	public R update(@RequestBody WmsTransferOrderEntity wmsTransferOrder){
		wmsTransferOrder.setModifierId(getUserId());
		wmsTransferOrder.setModifyDate(new Date());

		wmsTransferOrderService.update(wmsTransferOrder);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除仓库入出库单")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferorder:delete")
	public R delete(@RequestBody String[] warehouseCodes){
		wmsTransferOrderService.deleteBatch(warehouseCodes,getUserId());

		return R.ok();
	}

}
