package com.ltsznh.modules.sys.dao;

import com.ltsznh.modules.sys.entity.SysUserEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户，不限制用户机构权限
	 * @param map
	 * @return
	 */
	List<SysUserEntity> queryAllStaff(Map<String, Object> map);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 根据手机号，查询系统用户
	 */
	SysUserEntity queryByPhone(long phone);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
}
