package com.ltsznh.modules.pub.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.ltsznh.modules.pub.entity.PubMaterialTypeEntity;
import com.ltsznh.modules.pub.service.PubMaterialTypeService;


/**
 * 物料类型
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
@RestController
@RequestMapping("pubmaterialtype")
public class PubMaterialTypeController  extends AbstractController {
	@Autowired
	private PubMaterialTypeService pubMaterialTypeService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubmaterialtype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubMaterialTypeEntity> pubMaterialTypeList = pubMaterialTypeService.queryList(query);
		int total = pubMaterialTypeService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubMaterialTypeList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{materialTypeCode}")
	@RequiresPermissions("pubmaterialtype:info")
	public R info(@PathVariable("materialTypeCode") String materialTypeCode){
		PubMaterialTypeEntity pubMaterialType = pubMaterialTypeService.queryObject(materialTypeCode);

		return R.ok().put("pubMaterialType", pubMaterialType);
	}

	/**
	 * 保存
	 */
	@SysLog("保存物料类型")
	@RequestMapping("/save")
	@RequiresPermissions("pubmaterialtype:save")
	public R save(@RequestBody PubMaterialTypeEntity pubMaterialType){
		pubMaterialType.setCreatorId(getUserId());
		pubMaterialType.setCreateDate(new Date());

		pubMaterialTypeService.save(pubMaterialType);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改物料类型")
	@RequestMapping("/update")
	@RequiresPermissions("pubmaterialtype:update")
	public R update(@RequestBody PubMaterialTypeEntity pubMaterialType){
		pubMaterialType.setModifierId(getUserId());
		pubMaterialType.setModifyDate(new Date());

		pubMaterialTypeService.update(pubMaterialType);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除物料类型")
	@RequestMapping("/delete")
	@RequiresPermissions("pubmaterialtype:delete")
	public R delete(@RequestBody String[] materialTypeCodes){
		pubMaterialTypeService.deleteBatch(materialTypeCodes,getUserId());

		return R.ok();
	}

}
