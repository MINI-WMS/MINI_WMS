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

import com.ltsznh.modules.wms.entity.WmsTransferOrderAllEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderAllService;


/**
 * 仓库库存
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-03-19 15:19:41
 */
@RestController
@RequestMapping("wmstransferorderall")
public class WmsTransferOrderAllController  extends AbstractController {
	@Autowired
	private WmsTransferOrderAllService wmsTransferOrderAllService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorderall:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        params.put("userId",getUserId());
        Query query = new Query(params);

		List<WmsTransferOrderAllEntity> wmsTransferOrderAllList = wmsTransferOrderAllService.queryList(query);
		int total = wmsTransferOrderAllService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderAllList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{warehouseCode}")
	@RequiresPermissions("wmstransferorderall:info")
	public R info(@PathVariable("warehouseCode") String warehouseCode){
		WmsTransferOrderAllEntity wmsTransferOrderAll = wmsTransferOrderAllService.queryObject(warehouseCode);

		return R.ok().put("wmsTransferOrderAll", wmsTransferOrderAll);
	}
}
