package com.ltsznh.modules.sys.service;

import java.util.List;


/**
 * 角色与菜单对应关系
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService {
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList,Long userId);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
	
}
