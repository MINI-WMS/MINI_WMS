package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.PubSnDao;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import com.ltsznh.modules.wms.service.PubSnService;



@Service("pubSnService")
public class PubSnServiceImpl implements PubSnService {
	@Autowired
	private PubSnDao pubSnDao;

	@Override
	public PubSnEntity queryObject(Long snId){
		return pubSnDao.queryObject(snId);
	}

	@Override
	public List<PubSnEntity> queryList(Map<String, Object> map){
		return pubSnDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubSnDao.queryTotal(map);
	}

	@Override
	public void save(PubSnEntity pubSn){
		pubSnDao.save(pubSn);
	}

	@Override
	public void update(PubSnEntity pubSn){
		pubSnDao.update(pubSn);
	}

	@Override
	public void delete(Long snId,Long userId){
		pubSnDao.delete(snId,userId);
	}

	@Override
	public void deleteBatch(Long[] snIds,Long userId){	pubSnDao.deleteBatch(snIds,userId);}

	@Override
	public void DayEndClearing(Long[] snIds,Long userId){	pubSnDao.DayEndClearing(snIds,userId);}

	@Override
	public void DayEndClearing2(Long[] snIds,Long userId){	pubSnDao.DayEndClearing2(snIds,userId);}

}
