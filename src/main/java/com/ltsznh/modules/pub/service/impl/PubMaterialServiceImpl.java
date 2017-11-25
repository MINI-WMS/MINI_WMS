package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubMaterialDao;
import com.ltsznh.modules.pub.entity.PubMaterialEntity;
import com.ltsznh.modules.pub.service.PubMaterialService;



@Service("pubMaterialService")
public class PubMaterialServiceImpl implements PubMaterialService {
	@Autowired
	private PubMaterialDao pubMaterialDao;

	@Override
	public PubMaterialEntity queryObject(String materialCode){
		return pubMaterialDao.queryObject(materialCode);
	}

	@Override
	public List<PubMaterialEntity> queryList(Map<String, Object> map){
		return pubMaterialDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubMaterialDao.queryTotal(map);
	}

	@Override
	public void save(PubMaterialEntity pubMaterial){
		pubMaterialDao.save(pubMaterial);
	}

	@Override
	public void update(PubMaterialEntity pubMaterial){
		pubMaterialDao.update(pubMaterial);
	}

	@Override
	public void delete(String materialCode,Long userId){
		pubMaterialDao.delete(materialCode,userId);
	}

	@Override
	public void deleteBatch(String[] materialCodes,Long userId){	pubMaterialDao.deleteBatch(materialCodes,userId);}

}
