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

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;
import com.ltsznh.modules.sal.service.WmsTransferOrderSalService;


/**
 * 销售出库
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
@RestController
@RequestMapping("wmstransferordersal")
public class WmsTransferOrderSalController  extends AbstractController {
	@Autowired
	private WmsTransferOrderSalService wmsTransferOrderSalService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferordersal:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderSalEntity> wmsTransferOrderSalList = wmsTransferOrderSalService.queryList(query);
		int total = wmsTransferOrderSalService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderSalList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{toSalId}")
	@RequiresPermissions("wmstransferordersal:info")
	public R info(@PathVariable("toSalId") Long toSalId){
		WmsTransferOrderSalEntity wmsTransferOrderSal = wmsTransferOrderSalService.queryObject(toSalId);

		return R.ok().put("wmsTransferOrderSal", wmsTransferOrderSal);
	}

	/**
	 * 保存
	 */
	@SysLog("保存销售出库")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferordersal:save")
	public R save(@RequestBody WmsTransferOrderSalEntity wmsTransferOrderSal){
		wmsTransferOrderSal.setCreatorId(getUserId());
		wmsTransferOrderSal.setCreateDate(new Date());

		wmsTransferOrderSalService.save(wmsTransferOrderSal);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改销售出库")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferordersal:update")
	public R update(@RequestBody WmsTransferOrderSalEntity wmsTransferOrderSal){
		wmsTransferOrderSal.setModifierId(getUserId());
		wmsTransferOrderSal.setModifyDate(new Date());

		wmsTransferOrderSalService.update(wmsTransferOrderSal);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除销售出库")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferordersal:delete")
	public R delete(@RequestBody Long[] toSalIds){
		wmsTransferOrderSalService.deleteBatch(toSalIds,getUserId());

		return R.ok();
	}

}
