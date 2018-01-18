package com.ltsznh.modules.wms.controller;

import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.service.PubSnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 单据日期
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-12 11:28:32
 */
@RestController
@RequestMapping("pubsn")
public class PubSnController extends AbstractController {
	@Autowired
	private PubSnService pubSnService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubsn:list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);

		List<PubSnEntity> pubSnList = pubSnService.queryList(query);
		int total = pubSnService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubSnList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{snId}")
	@RequiresPermissions("pubsn:info")
	public R info(@PathVariable("snId") Long snId) {
		PubSnEntity pubSn = pubSnService.queryObject(snId);

		return R.ok().put("pubSn", pubSn);
	}

//	/**
//	 * 保存
//	 */
//	@SysLog("保存单据日期")
//	@RequestMapping("/save")
//	@RequiresPermissions("pubsn:save")
//	public R save(@RequestBody PubSnEntity pubSn){
//		pubSn.setCreatorId(getUserId());
//		pubSn.setCreateDate(new Date());
//
//		pubSnService.save(pubSn);
//
//		return R.ok();
//	}

//	r

//	/**
//	 * 删除
//	 */
//	@SysLog("删除单据日期")
//	@RequestMapping("/delete")
//	@RequiresPermissions("pubsn:delete")
//	public R delete(@RequestBody Long[] snIds){
//		pubSnService.deleteBatch(snIds,getUserId());
//
//		return R.ok();
//	}

	/**
	 * 日结
	 */
	@SysLog("日结")
	@RequestMapping("/DayEndClearing")
	@RequiresPermissions("pubsn:update")
	public R DayEndClearing(@RequestBody Long[] snIds) {
		pubSnService.DayEndClearing(snIds, getUserId());

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("反日结")
	@RequestMapping("/DayEndClearing2")
	@RequiresPermissions("pubsn:update")
	public R DayEndClearing2(@RequestBody Long[] snIds) {
		pubSnService.DayEndClearing2(snIds, getUserId());

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
