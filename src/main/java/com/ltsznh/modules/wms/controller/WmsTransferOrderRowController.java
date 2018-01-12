package com.ltsznh.modules.wms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.entity.WmsTransferOrderEntity;
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

import com.ltsznh.modules.wms.entity.WmsTransferOrderRowEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderRowService;


/**
 * 仓库入出库单明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:04
 */
@RestController
@RequestMapping("wmstransferorderrow")
public class WmsTransferOrderRowController  extends AbstractController {
	@Autowired
	private WmsTransferOrderRowService wmsTransferOrderRowService;
	@Autowired
	private PubSnService pubSnService;

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
	@RequestMapping("/info/{materialCode}")
	@RequiresPermissions("wmstransferorderrow:info")
	public R info(@PathVariable("materialCode") String materialCode){
		WmsTransferOrderRowEntity wmsTransferOrderRow = wmsTransferOrderRowService.queryObject(materialCode);

		return R.ok().put("wmsTransferOrderRow", wmsTransferOrderRow);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库入出库单明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorderrow:save")
	public R save(@RequestBody WmsTransferOrderRowEntity wmsTransferOrderRow){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderRow.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
	public R delete(@RequestBody String[] materialCodes){
		for (int i = 0; i < materialCodes.length; i++) {
			WmsTransferOrderRowEntity wmsTransferOrderRow = wmsTransferOrderRowService.queryObject(materialCodes[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderRow.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderRowService.deleteBatch(materialCodes,getUserId());

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
