package com.ltsznh.modules.sys.service;

import java.util.List;


/**
 * 角色与部门对应关系
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017年6月21日 23:42:30
 */
public interface SysRoleDeptService {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList,Long modifierId);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long roleId);
	
}
