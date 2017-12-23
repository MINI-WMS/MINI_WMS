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

import com.ltsznh.modules.wms.entity.WmsTransferOrderSnEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderSnService;


/**
 * 物料SN明细
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:53
 */
@RestController
@RequestMapping("wmstransferordersn")
public class WmsTransferOrderSnController  extends AbstractController {
	@Autowired
	private WmsTransferOrderSnService wmsTransferOrderSnService;

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
	@RequestMapping("/info/{materialCode}")
	@RequiresPermissions("wmstransferordersn:info")
	public R info(@PathVariable("materialCode") String materialCode){
		WmsTransferOrderSnEntity wmsTransferOrderSn = wmsTransferOrderSnService.queryObject(materialCode);

		return R.ok().put("wmsTransferOrderSn", wmsTransferOrderSn);
	}

	/**
	 * 保存
	 */
	@SysLog("保存物料SN明细")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferordersn:save")
	public R save(@RequestBody WmsTransferOrderSnEntity wmsTransferOrderSn){
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
	public R delete(@RequestBody String[] materialCodes){
		wmsTransferOrderSnService.deleteBatch(materialCodes,getUserId());

		return R.ok();
	}

}
