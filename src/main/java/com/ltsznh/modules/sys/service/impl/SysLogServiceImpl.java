package com.ltsznh.modules.sys.service.impl;


import com.ltsznh.modules.sys.dao.SysLogDao;
import com.ltsznh.modules.sys.entity.SysLogEntity;
import com.ltsznh.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public SysLogEntity queryObject(Long id){
		return sysLogDao.queryObject(id);
	}
	
	@Override
	public List<SysLogEntity> queryList(Map<String, Object> map){
		return sysLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysLogDao.queryTotal(map);
	}

	@Override
	public void save(SysLogEntity sysLog){
		sysLogDao.save(sysLog);
	}
}
