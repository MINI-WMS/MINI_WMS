package com.ltsznh.modules.sal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;
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

import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalRowEntity;
import com.ltsznh.modules.sal.service.WmsTransferOrderSalRowService;


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
	@Autowired
	private PubSnService pubSnService;

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
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSalRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSalRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
		for (int i = 0; i < toSalRowIds.length; i++) {
			WmsTransferOrderSalRowEntity wmsTransferOrderSalRow = wmsTransferOrderSalRowService.queryObject(toSalRowIds[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderSalRow.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderSalRowService.deleteBatch(toSalRowIds,getUserId());

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
