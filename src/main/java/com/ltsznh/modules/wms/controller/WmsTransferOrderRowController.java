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

import com.ltsznh.modules.wms.entity.WmsTransferOrderRowEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderRowService;


/**
 * 仓库入出库单明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
@RestController
@RequestMapping("wmstransferorderrow")
public class WmsTransferOrderRowController  extends AbstractController {
	@Autowired
	private WmsTransferOrderRowService wmsTransferOrderRowService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorderrow:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderRowEntity> wmsTransferOrderRowList = wmsTransferOrderRowService.queryList(query);
		int total = wmsTransferOrderRowService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderRowList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{warehouseCode}")
	@RequiresPermissions("wmstransferorderrow:info")
	public R info(@PathVariable("warehouseCode") String warehouseCode){
		WmsTransferOrderRowEntity wmsTransferOrderRow = wmsTransferOrderRowService.queryObject(warehouseCode);

		return R.ok().put("wmsTransferOrderRow", wmsTransferOrderRow);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库入出库单明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorderrow:save")
	public R save(@RequestBody WmsTransferOrderRowEntity wmsTransferOrderRow){
		wmsTransferOrderRow.setCreatorId(getUserId());
		wmsTransferOrderRow.setCreateDate(new Date());

		wmsTransferOrderRowService.save(wmsTransferOrderRow);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改仓库入出库单明细")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferorderrow:update")
	public R update(@RequestBody WmsTransferOrderRowEntity wmsTransferOrderRow){
		wmsTransferOrderRow.setModifierId(getUserId());
		wmsTransferOrderRow.setModifyDate(new Date());

		wmsTransferOrderRowService.update(wmsTransferOrderRow);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除仓库入出库单明细")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferorderrow:delete")
	public R delete(@RequestBody String[] warehouseCodes){
		wmsTransferOrderRowService.deleteBatch(warehouseCodes,getUserId());

		return R.ok();
	}

}
