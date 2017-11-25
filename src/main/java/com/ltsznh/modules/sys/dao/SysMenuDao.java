package com.ltsznh.modules.sys.dao;

import com.ltsznh.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentMenuId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentMenuId(Long parentMenuId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
