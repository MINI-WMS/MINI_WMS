package com.ltsznh.modules.sys.service;

import java.util.Date;
import java.util.List;


/**
 * 用户与角色对应关系
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList, Long creatorId, Date createDate);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	void delete(Long userId,Long modifierId);
}
