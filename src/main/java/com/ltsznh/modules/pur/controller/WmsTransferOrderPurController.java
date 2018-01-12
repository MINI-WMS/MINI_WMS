package com.ltsznh.modules.pur.controller;

import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;
import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.pur.service.WmsTransferOrderPurService;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.service.PubSnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 采购入库单
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
@RestController
@RequestMapping("wmstransferorderpur")
public class WmsTransferOrderPurController extends AbstractController {
	@Autowired
	private WmsTransferOrderPurService wmsTransferOrderPurService;
	@Autowired
	private PubSnService pubSnService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorderpur:list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);

		List<WmsTransferOrderPurEntity> wmsTransferOrderPurList = wmsTransferOrderPurService.queryList(query);
		int total = wmsTransferOrderPurService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderPurList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{toPurId}")
	@RequiresPermissions("wmstransferorderpur:info")
	public R info(@PathVariable("toPurId") Long toPurId) {
		WmsTransferOrderPurEntity wmsTransferOrderPur = wmsTransferOrderPurService.queryObject(toPurId);

		return R.ok().put("wmsTransferOrderPur", wmsTransferOrderPur);
	}

	/**
	 * 保存
	 */
	@SysLog("保存采购入库单")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorderpur:save")
	public R save(@RequestBody WmsTransferOrderPurEntity wmsTransferOrderPur) {
//		// 首先判断是否日结，如果日结不允许操作
//		R r = checkSnDate(wmsTransferOrderPur.getToDate());
//		if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		String snDate = wmsTransferOrderPur.getToDate();
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

		wmsTransferOrderPur.setCreatorId(getUserId());
		wmsTransferOrderPur.setCreateDate(new Date());

		wmsTransferOrderPurService.save(wmsTransferOrderPur);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改采购入库单")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferorderpur:update")
	public R update(@RequestBody WmsTransferOrderPurEntity wmsTransferOrderPur) {
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderPur.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrderPur.setModifierId(getUserId());
		wmsTransferOrderPur.setModifyDate(new Date());

		wmsTransferOrderPurService.update(wmsTransferOrderPur);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除采购入库单")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferorderpur:delete")
	public R delete(@RequestBody Long[] toPurIds) {
		for (int i = 0; i < toPurIds.length; i++) {
			WmsTransferOrderPurEntity wmsTransferOrderPur = wmsTransferOrderPurService.queryObject(toPurIds[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderPur.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}


		wmsTransferOrderPurService.deleteBatch(toPurIds, getUserId());

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
