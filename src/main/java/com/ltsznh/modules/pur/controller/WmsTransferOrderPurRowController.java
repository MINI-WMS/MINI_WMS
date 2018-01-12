package com.ltsznh.modules.pur.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.modules.wms.controller.PubSnController;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.service.PubSnService;
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
	@Autowired
	private PubSnService pubSnService;
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
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderPurRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderPurRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
		for (int i = 0; i < toPurRowIds.length; i++) {
			WmsTransferOrderPurRowEntity wmsTransferOrderRowPur = wmsTransferOrderPurRowService.queryObject(toPurRowIds[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderRowPur.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderPurRowService.deleteBatch(toPurRowIds,getUserId());

		return R.ok();
	}

	public R checkSnDate(String snDate) {
		// 首先判断是否日结，如果日结不允许操作
		Map<String, Object> params = new HashMap<>();
		params.put("snDate", snDate);
		Query query = new Query(params);
		List<PubSnEntity> pubSnList = pubSnService.queryList(query);
		if (pubSnList.size() <= 0) {// 无指定类型单据
			return R.error("单据日期：" + snDate + "；该日期无效！");
		} else if (pubSnList.get(0).getSnStatus() > 1) {// 1正常；2日结
			return R.error("单据日期：" + snDate + "；该日期已经日结！");
		}
		return R.ok();
	}

}
