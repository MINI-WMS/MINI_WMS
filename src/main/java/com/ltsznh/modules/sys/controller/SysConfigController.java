package com.ltsznh.modules.sys.controller;


import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.modules.sys.entity.SysConfigEntity;
import com.ltsznh.modules.sys.service.SysConfigService;
import com.ltsznh.common.utils.PageUtils;
import com.ltsznh.common.utils.Query;
import com.ltsznh.common.utils.R;
import com.ltsznh.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月4日 下午6:55:53
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public R list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		List<SysConfigEntity> configList = sysConfigService.queryList(query);
		int total = sysConfigService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public R info(@PathVariable("id") Long id) {
		SysConfigEntity config = sysConfigService.queryObject(id);

		return R.ok().put("config", config);
	}

	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public R save(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		config.setCreatorId(getUserId());
		config.setCreateDate(new Date());
		sysConfigService.save(config);

		return R.ok();
	}

	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public R update(@RequestBody SysConfigEntity config) {
		ValidatorUtils.validateEntity(config);
		config.setModifierId(getUserId());
		config.setModifyDate(new Date());
		sysConfigService.update(config);

		return R.ok();
	}

	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public R delete(@RequestBody Long[] ids) {
		sysConfigService.deleteBatch(ids, getUserId());

		return R.ok();
	}

}
