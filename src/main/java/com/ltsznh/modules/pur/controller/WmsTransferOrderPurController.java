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

import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.pur.service.WmsTransferOrderPurService;


/**
 * 采购入库单
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
@RestController
@RequestMapping("wmstransferorderpur")
public class WmsTransferOrderPurController  extends AbstractController {
	@Autowired
	private WmsTransferOrderPurService wmsTransferOrderPurService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorderpur:list")
	public R list(@RequestParam Map<String, Object> params){
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
	public R info(@PathVariable("toPurId") Long toPurId){
		WmsTransferOrderPurEntity wmsTransferOrderPur = wmsTransferOrderPurService.queryObject(toPurId);

		return R.ok().put("wmsTransferOrderPur", wmsTransferOrderPur);
	}

	/**
	 * 保存
	 */
	@SysLog("保存采购入库单")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorderpur:save")
	public R save(@RequestBody WmsTransferOrderPurEntity wmsTransferOrderPur){
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
	public R update(@RequestBody WmsTransferOrderPurEntity wmsTransferOrderPur){
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
	public R delete(@RequestBody Long[] toPurIds){
		wmsTransferOrderPurService.deleteBatch(toPurIds,getUserId());

		return R.ok();
	}

}
