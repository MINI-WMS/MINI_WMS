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

import com.ltsznh.modules.pub.entity.PubUnitEntity;
import com.ltsznh.modules.pub.service.PubUnitService;


/**
 * 单位
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
@RestController
@RequestMapping("pubunit")
public class PubUnitController  extends AbstractController {
	@Autowired
	private PubUnitService pubUnitService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubunit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubUnitEntity> pubUnitList = pubUnitService.queryList(query);
		int total = pubUnitService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubUnitList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{unitCode}")
	@RequiresPermissions("pubunit:info")
	public R info(@PathVariable("unitCode") String unitCode){
		PubUnitEntity pubUnit = pubUnitService.queryObject(unitCode);

		return R.ok().put("pubUnit", pubUnit);
	}

	/**
	 * 保存
	 */
	@SysLog("保存单位")
	@RequestMapping("/save")
	@RequiresPermissions("pubunit:save")
	public R save(@RequestBody PubUnitEntity pubUnit){
		pubUnit.setCreatorId(getUserId());
		pubUnit.setCreateDate(new Date());

		pubUnitService.save(pubUnit);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改单位")
	@RequestMapping("/update")
	@RequiresPermissions("pubunit:update")
	public R update(@RequestBody PubUnitEntity pubUnit){
		pubUnit.setModifierId(getUserId());
		pubUnit.setModifyDate(new Date());

		pubUnitService.update(pubUnit);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除单位")
	@RequestMapping("/delete")
	@RequiresPermissions("pubunit:delete")
	public R delete(@RequestBody String[] unitCodes){
		pubUnitService.deleteBatch(unitCodes,getUserId());

		return R.ok();
	}

}
