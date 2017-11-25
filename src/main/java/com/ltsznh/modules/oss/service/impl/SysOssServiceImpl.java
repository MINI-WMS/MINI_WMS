package com.ltsznh.modules.oss.service.impl;

import com.ltsznh.modules.oss.dao.SysOssDao;
import com.ltsznh.modules.oss.entity.SysOssEntity;
import com.ltsznh.modules.oss.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {
	@Autowired
	private SysOssDao sysOssDao;
	
	@Override
	public SysOssEntity queryObject(Long id){
		return sysOssDao.queryObject(id);
	}
	
	@Override
	public List<SysOssEntity> queryList(Map<String, Object> map){
		return sysOssDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysOssDao.queryTotal(map);
	}
	
	@Override
	public void save(SysOssEntity sysOss){
		sysOssDao.save(sysOss);
	}
	
	@Override
	public void update(SysOssEntity sysOss){
		sysOssDao.update(sysOss);
	}
	
	@Override
	public void delete(Long id,Long userId){
		sysOssDao.delete(id,userId);
	}
	
	@Override
	public void deleteBatch(Long[] ids,Long userId){
		sysOssDao.deleteBatch(ids,userId);
	}
	
}
