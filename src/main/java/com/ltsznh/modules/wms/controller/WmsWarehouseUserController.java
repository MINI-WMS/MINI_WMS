package com.ltsznh.modules.wms.controller;

import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.modules.wms.entity.WmsWarehouseUserEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户仓库权限管理
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
@RestController
@RequestMapping("wmswarehouseuser")
public class WmsWarehouseUserController extends AbstractController {
	@Autowired
	private WmsWarehouseUserService wmsWarehouseUserService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmswarehouseuser:list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);

		List<WmsWarehouseUserEntity> wmsWarehouseUserList = wmsWarehouseUserService.queryList(query);
		int total = wmsWarehouseUserService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsWarehouseUserList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


//	/**
//	 * 信息
//	 */
//	@RequestMapping("/info/{wmsUserId}")
//	@RequiresPermissions("wmswarehouseuser:info")
//	public R info(@PathVariable("wmsUserId") Long wmsUserId){
//		WmsWarehouseUserEntity wmsWarehouseUser = wmsWarehouseUserService.queryObject(wmsUserId);
//
//		return R.ok().put("wmsWarehouseUser", wmsWarehouseUser);
//	}

	/**
	 * 信息
	 */
	@RequestMapping("/myWarehouse")
	@RequiresPermissions("wmswarehouseuser:info")
	public R myWarehouse() {
		return userWarehouse(getUserId());
	}

	/**
	 * 信息
	 */
	@RequestMapping("/myWarehouse/{userId}")
	@RequiresPermissions("wmswarehouseuser:info")
	public R userWarehouse(@PathVariable("userId") Long wmsUserId) {
		Long userId = wmsUserId > 0 ? wmsUserId : getUserId();

		Map<String, Object> params = new HashMap<>();
		params.put("userId",userId);

		//查询列表数据
		Query query = new Query(params);

		List<WmsWarehouseUserEntity> wmsWarehouseUserList = wmsWarehouseUserService.queryList(query);
		int total = wmsWarehouseUserService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsWarehouseUserList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{wmsUserId}")
	@RequiresPermissions("wmswarehouseuser:info")
	public R info(@PathVariable("wmsUserId") Long wmsUserId) {
		WmsWarehouseUserEntity wmsWarehouseUser = wmsWarehouseUserService.queryObject(wmsUserId);

		//查询用户对应的仓库
		List<String> warehouseCodeList = wmsWarehouseUserService.queryWarehouseCodeList(wmsWarehouseUser.getUserId());
		wmsWarehouseUser.setWarehouseCodeList(warehouseCodeList);

		return R.ok().put("wmsWarehouseUser", wmsWarehouseUser);
	}

	/**
	 * 保存
	 */
	@SysLog("保存用户仓库权限管理")
	@RequestMapping("/save")
	@RequiresPermissions("wmswarehouseuser:save")
	public R save(@RequestBody WmsWarehouseUserEntity wmsWarehouseUser) {
		wmsWarehouseUser.setCreatorId(getUserId());
		wmsWarehouseUser.setCreateDate(new Date());

		delete(wmsWarehouseUser.getUserId());

		wmsWarehouseUserService.save(wmsWarehouseUser);

		return R.ok();
	}


	/**
	 * 删除
	 */
	@SysLog("删除用户仓库权限管理")
	@RequestMapping("/delete")
	@RequiresPermissions("wmswarehouseuser:delete")
	public R delete(@RequestBody Long wmsUserId) {
		wmsWarehouseUserService.delete(wmsUserId, getUserId());

		return R.ok();
	}

}
