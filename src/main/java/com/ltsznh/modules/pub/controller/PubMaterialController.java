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

import com.ltsznh.modules.pub.entity.PubMaterialEntity;
import com.ltsznh.modules.pub.service.PubMaterialService;


/**
 * 物料
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 11:29:48
 */
@RestController
@RequestMapping("pubmaterial")
public class PubMaterialController  extends AbstractController {
	@Autowired
	private PubMaterialService pubMaterialService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubmaterial:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubMaterialEntity> pubMaterialList = pubMaterialService.queryList(query);
		int total = pubMaterialService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubMaterialList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/select2")
	public R select2(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<PubMaterialEntity> pubMaterialList = pubMaterialService.queryList(query);

		PageUtils pageUtil = new PageUtils(pubMaterialList, 0, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{materialCode}")
	@RequiresPermissions("pubmaterial:info")
	public R info(@PathVariable("materialCode") String materialCode){
		PubMaterialEntity pubMaterial = pubMaterialService.queryObject(materialCode);

		return R.ok().put("pubMaterial", pubMaterial);
	}

	/**
	 * 保存
	 */
	@SysLog("保存物料")
	@RequestMapping("/save")
	@RequiresPermissions("pubmaterial:save")
	public R save(@RequestBody PubMaterialEntity pubMaterial){
		pubMaterial.setCreatorId(getUserId());
		pubMaterial.setCreateDate(new Date());

		pubMaterialService.save(pubMaterial);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改物料")
	@RequestMapping("/update")
	@RequiresPermissions("pubmaterial:update")
	public R update(@RequestBody PubMaterialEntity pubMaterial){
		pubMaterial.setModifierId(getUserId());
		pubMaterial.setModifyDate(new Date());

		pubMaterialService.update(pubMaterial);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除物料")
	@RequestMapping("/delete")
	@RequiresPermissions("pubmaterial:delete")
	public R delete(@RequestBody String[] materialCodes){
		pubMaterialService.deleteBatch(materialCodes,getUserId());

		return R.ok();
	}

}
