package com.ltsznh.modules.sys.service.impl;

import com.ltsznh.common.annotation.DataFilter;
import com.ltsznh.modules.sys.dao.SysRoleDao;
import com.ltsznh.modules.sys.entity.SysRoleEntity;
import com.ltsznh.modules.sys.service.SysRoleDeptService;
import com.ltsznh.modules.sys.service.SysRoleMenuService;
import com.ltsznh.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;

	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.queryObject(roleId);
	}

	@Override
	@DataFilter(tableAlias = "r", user = false)
	public List<SysRoleEntity> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	@Override
	@DataFilter(tableAlias = "r", user = false)
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		sysRoleDao.save(role);

		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(), role.getCreatorId());

		//保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList(), role.getCreatorId());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);

		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(),role.getCreatorId());

		//保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList(),role.getCreatorId());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds, Long userId) {
		sysRoleDao.deleteBatch(roleIds, userId);
	}

}
