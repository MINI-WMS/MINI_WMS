package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubAreaDao;
import com.ltsznh.modules.pub.entity.PubAreaEntity;
import com.ltsznh.modules.pub.service.PubAreaService;



@Service("pubAreaService")
public class PubAreaServiceImpl implements PubAreaService {
	@Autowired
	private PubAreaDao pubAreaDao;

	@Override
	public PubAreaEntity queryObject(String areaCode){
		return pubAreaDao.queryObject(areaCode);
	}

	@Override
	public List<PubAreaEntity> queryList(Map<String, Object> map){
		return pubAreaDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubAreaDao.queryTotal(map);
	}

	@Override
	public void save(PubAreaEntity pubArea){
		pubAreaDao.save(pubArea);
	}

	@Override
	public void update(PubAreaEntity pubArea){
		pubAreaDao.update(pubArea);
	}

	@Override
	public void delete(String areaCode,Long userId){
		pubAreaDao.delete(areaCode,userId);
	}

	@Override
	public void deleteBatch(String[] areaCodes,Long userId){	pubAreaDao.deleteBatch(areaCodes,userId);}

}
