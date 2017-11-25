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

import com.ltsznh.modules.pub.entity.PubEnterpriseEntity;
import com.ltsznh.modules.pub.service.PubEnterpriseService;


/**
 * 企业
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
@RestController
@RequestMapping("pubenterprise")
public class PubEnterpriseController  extends AbstractController {
	@Autowired
	private PubEnterpriseService pubEnterpriseService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("pubenterprise:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubEnterpriseEntity> pubEnterpriseList = pubEnterpriseService.queryList(query);
		int total = pubEnterpriseService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(pubEnterpriseList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{enterpriseCode}")
	@RequiresPermissions("pubenterprise:info")
	public R info(@PathVariable("enterpriseCode") String enterpriseCode){
		PubEnterpriseEntity pubEnterprise = pubEnterpriseService.queryObject(enterpriseCode);

		return R.ok().put("pubEnterprise", pubEnterprise);
	}

	/**
	 * 保存
	 */
	@SysLog("保存企业")
	@RequestMapping("/save")
	@RequiresPermissions("pubenterprise:save")
	public R save(@RequestBody PubEnterpriseEntity pubEnterprise){
		pubEnterprise.setCreatorId(getUserId());
		pubEnterprise.setCreateDate(new Date());

		pubEnterpriseService.save(pubEnterprise);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改企业")
	@RequestMapping("/update")
	@RequiresPermissions("pubenterprise:update")
	public R update(@RequestBody PubEnterpriseEntity pubEnterprise){
		pubEnterprise.setModifierId(getUserId());
		pubEnterprise.setModifyDate(new Date());

		pubEnterpriseService.update(pubEnterprise);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除企业")
	@RequestMapping("/delete")
	@RequiresPermissions("pubenterprise:delete")
	public R delete(@RequestBody String[] enterpriseCodes){
		pubEnterpriseService.deleteBatch(enterpriseCodes,getUserId());

		return R.ok();
	}

}
