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

import com.ltsznh.modules.pub.entity.PubBrandEntity;
import com.ltsznh.modules.pub.service.PubBrandService;


/**
 * 品牌管理
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-08 15:36:01
 */
@RestController
@RequestMapping("pubbrand")
public class PubBrandController  extends AbstractController {
	@Autowired
	private PubBrandService pubBrandService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubbrand:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubBrandEntity> pubBrandList = pubBrandService.queryList(query);
		int total = pubBrandService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubBrandList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{brandId}")
	@RequiresPermissions("pubbrand:info")
	public R info(@PathVariable("brandId") Long brandId){
		PubBrandEntity pubBrand = pubBrandService.queryObject(brandId);

		return R.ok().put("pubBrand", pubBrand);
	}

	/**
	 * 保存
	 */
	@SysLog("保存品牌管理")
	@RequestMapping("/save")
	@RequiresPermissions("pubbrand:save")
	public R save(@RequestBody PubBrandEntity pubBrand){
		pubBrand.setCreatorId(getUserId());
		pubBrand.setCreateDate(new Date());

		pubBrandService.save(pubBrand);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改品牌管理")
	@RequestMapping("/update")
	@RequiresPermissions("pubbrand:update")
	public R update(@RequestBody PubBrandEntity pubBrand){
		pubBrand.setModifierId(getUserId());
		pubBrand.setModifyDate(new Date());

		pubBrandService.update(pubBrand);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除品牌管理")
	@RequestMapping("/delete")
	@RequiresPermissions("pubbrand:delete")
	public R delete(@RequestBody Long[] brandIds){
		pubBrandService.deleteBatch(brandIds,getUserId());

		return R.ok();
	}

}
