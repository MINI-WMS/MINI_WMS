package com.ltsznh.modules.sys.service.impl;

import com.ltsznh.modules.sys.dao.SysRoleDeptDao;
import com.ltsznh.modules.sys.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色与部门对应关系
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017年6月21日 23:42:30
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> deptIdList,Long modifierId) {
		//先删除角色与部门关系
		sysRoleDeptDao.delete(roleId,modifierId);

		if(deptIdList.size() == 0){
			return ;
		}

		//保存角色与部门关系
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		map.put("deptIdList", deptIdList);
		map.put("creatorId",modifierId);
		sysRoleDeptDao.save(map);
	}

	@Override
	public List<Long> queryDeptIdList(Long roleId) {
		return sysRoleDeptDao.queryDeptIdList(roleId);
	}

}
