package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubUnitDao;
import com.ltsznh.modules.pub.entity.PubUnitEntity;
import com.ltsznh.modules.pub.service.PubUnitService;



@Service("pubUnitService")
public class PubUnitServiceImpl implements PubUnitService {
	@Autowired
	private PubUnitDao pubUnitDao;

	@Override
	public PubUnitEntity queryObject(String unitCode){
		return pubUnitDao.queryObject(unitCode);
	}

	@Override
	public List<PubUnitEntity> queryList(Map<String, Object> map){
		return pubUnitDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubUnitDao.queryTotal(map);
	}

	@Override
	public void save(PubUnitEntity pubUnit){
		pubUnitDao.save(pubUnit);
	}

	@Override
	public void update(PubUnitEntity pubUnit){
		pubUnitDao.update(pubUnit);
	}

	@Override
	public void delete(String unitCode,Long userId){
		pubUnitDao.delete(unitCode,userId);
	}

	@Override
	public void deleteBatch(String[] unitCodes,Long userId){	pubUnitDao.deleteBatch(unitCodes,userId);}

}
