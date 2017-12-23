package com.ltsznh.modules.wms.controller;

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

import com.ltsznh.modules.wms.entity.WmsMoveTypeEntity;
import com.ltsznh.modules.wms.service.WmsMoveTypeService;


/**
 * 库存移动类型
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:53
 */
@RestController
@RequestMapping("wmsmovetype")
public class WmsMoveTypeController  extends AbstractController {
	@Autowired
	private WmsMoveTypeService wmsMoveTypeService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("wmsmovetype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<WmsMoveTypeEntity> wmsMoveTypeList = wmsMoveTypeService.queryList(query);
		int total = wmsMoveTypeService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(wmsMoveTypeList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{moveTypeCode}")
	@RequiresPermissions("wmsmovetype:info")
	public R info(@PathVariable("moveTypeCode") String moveTypeCode){
		WmsMoveTypeEntity wmsMoveType = wmsMoveTypeService.queryObject(moveTypeCode);

		return R.ok().put("wmsMoveType", wmsMoveType);
	}

	/**
	 * 保存
	 */
	@SysLog("保存库存移动类型")
	@RequestMapping("/save")
	@RequiresPermissions("wmsmovetype:save")
	public R save(@RequestBody WmsMoveTypeEntity wmsMoveType){
		wmsMoveType.setCreatorId(getUserId());
		wmsMoveType.setCreateDate(new Date());

		wmsMoveTypeService.save(wmsMoveType);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改库存移动类型")
	@RequestMapping("/update")
	@RequiresPermissions("wmsmovetype:update")
	public R update(@RequestBody WmsMoveTypeEntity wmsMoveType){
		wmsMoveType.setModifierId(getUserId());
		wmsMoveType.setModifyDate(new Date());

		wmsMoveTypeService.update(wmsMoveType);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除库存移动类型")
	@RequestMapping("/delete")
	@RequiresPermissions("wmsmovetype:delete")
	public R delete(@RequestBody String[] moveTypeCodes){
		wmsMoveTypeService.deleteBatch(moveTypeCodes,getUserId());

		return R.ok();
	}

}
