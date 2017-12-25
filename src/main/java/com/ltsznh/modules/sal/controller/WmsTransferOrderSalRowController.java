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

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalRowEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderSalRowService;


/**
 * 销售出库明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
@RestController
@RequestMapping("wmstransferordersalrow")
public class WmsTransferOrderSalRowController  extends AbstractController {
	@Autowired
	private WmsTransferOrderSalRowService wmsTransferOrderSalRowService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferordersalrow:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderSalRowEntity> wmsTransferOrderSalRowList = wmsTransferOrderSalRowService.queryList(query);
		int total = wmsTransferOrderSalRowService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderSalRowList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{toSalRowId}")
	@RequiresPermissions("wmstransferordersalrow:info")
	public R info(@PathVariable("toSalRowId") Long toSalRowId){
		WmsTransferOrderSalRowEntity wmsTransferOrderSalRow = wmsTransferOrderSalRowService.queryObject(toSalRowId);

		return R.ok().put("wmsTransferOrderSalRow", wmsTransferOrderSalRow);
	}

	/**
	 * 保存
	 */
	@SysLog("保存销售出库明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferordersalrow:save")
	public R save(@RequestBody WmsTransferOrderSalRowEntity wmsTransferOrderSalRow){
		wmsTransferOrderSalRow.setCreatorId(getUserId());
		wmsTransferOrderSalRow.setCreateDate(new Date());

		wmsTransferOrderSalRowService.save(wmsTransferOrderSalRow);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改销售出库明细")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferordersalrow:update")
	public R update(@RequestBody WmsTransferOrderSalRowEntity wmsTransferOrderSalRow){
		wmsTransferOrderSalRow.setModifierId(getUserId());
		wmsTransferOrderSalRow.setModifyDate(new Date());

		wmsTransferOrderSalRowService.update(wmsTransferOrderSalRow);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除销售出库明细")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferordersalrow:delete")
	public R delete(@RequestBody Long[] toSalRowIds){
		wmsTransferOrderSalRowService.deleteBatch(toSalRowIds,getUserId());

		return R.ok();
	}

}
