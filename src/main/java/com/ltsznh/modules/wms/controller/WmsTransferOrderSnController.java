package com.ltsznh.modules.wms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.entity.WmsTransferOrderRowEntity;
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

import com.ltsznh.modules.wms.entity.WmsTransferOrderSnEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderSnService;


/**
 * 物料SN明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
@RestController
@RequestMapping("wmstransferordersn")
public class WmsTransferOrderSnController  extends AbstractController {
	@Autowired
	private WmsTransferOrderSnService wmsTransferOrderSnService;
	@Autowired
	private PubSnService pubSnService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferordersn:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderSnEntity> wmsTransferOrderSnList = wmsTransferOrderSnService.queryList(query);
		int total = wmsTransferOrderSnService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderSnList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{toSnId}")
	@RequiresPermissions("wmstransferordersn:info")
	public R info(@PathVariable("toSnId") Long toSnId){
		WmsTransferOrderSnEntity wmsTransferOrderSn = wmsTransferOrderSnService.queryObject(toSnId);

		return R.ok().put("wmsTransferOrderSn", wmsTransferOrderSn);
	}

	/**
	 * 保存
	 */
	@SysLog("保存物料SN明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferordersn:save")
	public R save(@RequestBody WmsTransferOrderSnEntity wmsTransferOrderSn){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSn.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrderSn.setCreatorId(getUserId());
		wmsTransferOrderSn.setCreateDate(new Date());

		wmsTransferOrderSnService.save(wmsTransferOrderSn);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改物料SN明细")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferordersn:update")
	public R update(@RequestBody WmsTransferOrderSnEntity wmsTransferOrderSn){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSn.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrderSn.setModifierId(getUserId());
		wmsTransferOrderSn.setModifyDate(new Date());

		wmsTransferOrderSnService.update(wmsTransferOrderSn);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除物料SN明细")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferordersn:delete")
	public R delete(@RequestBody Long[] toSnIds){
		for (int i = 0; i < toSnIds.length; i++) {
			WmsTransferOrderSnEntity wmsTransferOrderSn = wmsTransferOrderSnService.queryObject(toSnIds[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderSn.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderSnService.deleteBatch(toSnIds,getUserId());

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
