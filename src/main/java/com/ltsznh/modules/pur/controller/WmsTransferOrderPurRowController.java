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

import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurRowEntity;
import com.ltsznh.modules.pur.service.WmsTransferOrderPurRowService;


/**
 * 采购入库单明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
@RestController
@RequestMapping("wmstransferorderpurrow")
public class WmsTransferOrderPurRowController  extends AbstractController {
	@Autowired
	private WmsTransferOrderPurRowService wmsTransferOrderPurRowService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorderpurrow:list")
	public List<WmsTransferOrderPurRowEntity> list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderPurRowEntity> wmsTransferOrderPurRowList = wmsTransferOrderPurRowService.queryList(query);
		return  wmsTransferOrderPurRowList;
//		int total = wmsTransferOrderPurRowService.queryTotal(query);
//
//		PageUtils pageUtil = new PageUtils(wmsTransferOrderPurRowList, total, query.getLimit(), query.getPage());
//
//		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{toPurRowId}")
	@RequiresPermissions("wmstransferorderpurrow:info")
	public R info(@PathVariable("toPurRowId") Long toPurRowId){
		WmsTransferOrderPurRowEntity wmsTransferOrderPurRow = wmsTransferOrderPurRowService.queryObject(toPurRowId);

		return R.ok().put("wmsTransferOrderPurRow", wmsTransferOrderPurRow);
	}

	/**
	 * 保存
	 */
	@SysLog("保存采购入库单明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorderpurrow:save")
	public R save(@RequestBody WmsTransferOrderPurRowEntity wmsTransferOrderPurRow){
		wmsTransferOrderPurRow.setCreatorId(getUserId());
		wmsTransferOrderPurRow.setCreateDate(new Date());

		wmsTransferOrderPurRowService.save(wmsTransferOrderPurRow);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改采购入库单明细")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferorderpurrow:update")
	public R update(@RequestBody WmsTransferOrderPurRowEntity wmsTransferOrderPurRow){
		wmsTransferOrderPurRow.setModifierId(getUserId());
		wmsTransferOrderPurRow.setModifyDate(new Date());

		wmsTransferOrderPurRowService.update(wmsTransferOrderPurRow);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除采购入库单明细")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferorderpurrow:delete")
	public R delete(@RequestBody Long[] toPurRowIds){
		wmsTransferOrderPurRowService.deleteBatch(toPurRowIds,getUserId());

		return R.ok();
	}

}
