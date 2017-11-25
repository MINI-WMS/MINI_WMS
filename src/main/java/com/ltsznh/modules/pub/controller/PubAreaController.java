package com.ltsznh.modules.pub.controller;

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

import com.ltsznh.modules.pub.entity.PubAreaEntity;
import com.ltsznh.modules.pub.service.PubAreaService;


/**
 * 地区
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
@RestController
@RequestMapping("pubarea")
public class PubAreaController  extends AbstractController {
	@Autowired
	private PubAreaService pubAreaService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubarea:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubAreaEntity> pubAreaList = pubAreaService.queryList(query);
		int total = pubAreaService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubAreaList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{areaCode}")
	@RequiresPermissions("pubarea:info")
	public R info(@PathVariable("areaCode") String areaCode){
		PubAreaEntity pubArea = pubAreaService.queryObject(areaCode);

		return R.ok().put("pubArea", pubArea);
	}

	/**
	 * 保存
	 */
	@SysLog("保存地区")
	@RequestMapping("/save")
	@RequiresPermissions("pubarea:save")
	public R save(@RequestBody PubAreaEntity pubArea){
		pubArea.setCreatorId(getUserId());
		pubArea.setCreateDate(new Date());

		pubAreaService.save(pubArea);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改地区")
	@RequestMapping("/update")
	@RequiresPermissions("pubarea:update")
	public R update(@RequestBody PubAreaEntity pubArea){
		pubArea.setModifierId(getUserId());
		pubArea.setModifyDate(new Date());

		pubAreaService.update(pubArea);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除地区")
	@RequestMapping("/delete")
	@RequiresPermissions("pubarea:delete")
	public R delete(@RequestBody String[] areaCodes){
		pubAreaService.deleteBatch(areaCodes,getUserId());

		return R.ok();
	}

}
