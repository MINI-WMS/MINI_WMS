package com.ltsznh.modules.sal.controller;

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
	@Autowired
	private PubSnService pubSnService;

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
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSal.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

		wmsTransferOrderSal.setCreatorId(getUserId());
		wmsTransferOrderSal.setCreateDate(new Date());

		wmsTransferOrderSalService.save(wmsTransferOrderSal);

        Map<String, Object> params = new HashMap<>();
        params.put("creatorId",getUserId());
        Query  query = new Query(params);

        List<WmsTransferOrderSalEntity> wmsTransferOrderSalList = wmsTransferOrderSalService.queryLatest(query);

        if(wmsTransferOrderSalList!=null &&!wmsTransferOrderSalList.isEmpty()&& wmsTransferOrderSalList.size()>0){
            return R.ok().put("toNo",wmsTransferOrderSalList.get(0).getToNo()).put("toId",wmsTransferOrderSalList.get(0).getToSalId());
        }

		return R.error("系统未知异常!");
	}

	/**
	 * 修改
	 */
	@SysLog("修改销售出库")
	@RequestMapping("/update")
	@RequiresPermissions("wmstransferordersal:update")
	public R update(@RequestBody WmsTransferOrderSalEntity wmsTransferOrderSal){
		// 首先判断是否日结，如果日结不允许操作
		R r = checkSnDate(wmsTransferOrderSal.getToDate());
		if (Integer.parseInt(r.get("code").toString()) != 0) return r;

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
		for (int i = 0; i < toSalIds.length; i++) {
			WmsTransferOrderSalEntity wmsTransferOrderSal = wmsTransferOrderSalService.queryObject(toSalIds[i]);

			// 首先判断是否日结，如果日结不允许操作
			R r = checkSnDate(wmsTransferOrderSal.getToDate());
			if (Integer.parseInt(r.get("code").toString()) != 0) return r;
		}

		wmsTransferOrderSalService.deleteBatch(toSalIds,getUserId());

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
