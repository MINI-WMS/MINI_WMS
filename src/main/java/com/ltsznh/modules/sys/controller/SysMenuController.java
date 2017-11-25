package com.ltsznh.modules.sys.controller;

import com.ltsznh.common.annotation.SysLog;
import com.ltsznh.common.exception.FamilyException;
import com.ltsznh.common.utils.Constant;
import com.ltsznh.common.utils.R;
import com.ltsznh.modules.sys.entity.SysMenuEntity;
import com.ltsznh.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 系统菜单
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	public R nav(){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list(){
		List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setMenuName("一级菜单");
		root.setParentMenuId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		menu.setCreatorId(getUserId());
		sysMenuService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		menu.setModifierId(getUserId());
		sysMenuService.update(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public R delete(long menuId){
		if(menuId <= 35){
			return R.error("系统菜单，不允许禁用");
		}

		//判断是否有子菜单或按钮
//		List<SysMenuEntity> menuList = sysMenuService.queryListParentMenuId(menuId);
//		if(menuList.size() > 0){
//			return R.error("请先删除子菜单或按钮");
//		}

		sysMenuService.deleteBatch(new Long[]{menuId},getUserId());
		
		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getMenuName())){
			throw new FamilyException("菜单名称不能为空");
		}
		
		if(menu.getParentMenuId() == null){
			throw new FamilyException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getMenuType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new FamilyException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentMenuId() != 0){
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentMenuId());
			parentType = parentMenu.getMenuType();
		}
		
		//目录、菜单
		if(menu.getMenuType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getMenuType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new FamilyException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getMenuType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new FamilyException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
