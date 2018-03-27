package com.ltsznh.modules.wms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
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

import com.ltsznh.modules.wms.entity.WmsTransferOrderEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderService;


/**
 * 仓库入出库单
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:03
 */
@RestController
@RequestMapping("wmstransferorder")
public class WmsTransferOrderController  extends AbstractController {
	@Autowired
	private WmsTransferOrderService wmsTransferOrderService;
	@Autowired
	private PubSnService pubSnService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmstransferorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsTransferOrderEntity> wmsTransferOrderList = wmsTransferOrderService.queryList(query);
		int total = wmsTransferOrderService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsTransferOrderList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{warehouseCode}")
	@RequiresPermissions("wmstransferorder:info")
	public R info(@PathVariable("warehouseCode") String warehouseCode){
		WmsTransferOrderEntity wmsTransferOrder = wmsTransferOrderService.queryObject(warehouseCode);

		return R.ok().put("wmsTransferOrder", wmsTransferOrder);
	}

	/**
	 * 保存
	 */
	@SysLog("保存仓库入出库单")
	@RequestMapping("/save")
	@RequiresPermissions("wmstransferorder:save")
	public R save(@RequestBody WmsTransferOrderEntity wmsTransferOrder){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrder.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrder.setCreatorId(getUserId());
		wmsTransferOrder.setCreateDate(new Date());

		wmsTransferOrderService.save(wmsTransferOrder);

        Map<String, Object> params = new HashMap<>();
        params.put("creatorId",getUserId());
        Query  query = new Query(params);

        List<WmsTransferOrderEntity> wmsTransferOrderList = wmsTransferOrderService.queryLatest(query);

        if(wmsTransferOrderList!=null &&!wmsTransferOrderList.isEmpty()&& wmsTransferOrderList.size()>0){
            return R.ok().put("toNo",wmsTransferOrderList.get(0).getToNo()).put("toId",wmsTransferOrderList.get(0).getToId());
        }

        return R.error("系统未知异常!");
	}

	/**
	 * 修改
	 */
	@SysLog("修改仓库入出库单")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferorder:update")
	public R update(@RequestBody WmsTransferOrderEntity wmsTransferOrder){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrder.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrder.setModifierId(getUserId());
		wmsTransferOrder.setModifyDate(new Date());

		wmsTransferOrderService.update(wmsTransferOrder);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除仓库入出库单")
	@RequestMapping("/delete")
	@RequiresPermissions("wmstransferorder:delete")
	public R delete(@RequestBody String[] warehouseCodes){
		for (int i = 0; i < warehouseCodes.length; i++) {
			WmsTransferOrderEntity wmsTransferOrder = wmsTransferOrderService.queryObject(warehouseCodes[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrder.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderService.deleteBatch(warehouseCodes,getUserId());

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
