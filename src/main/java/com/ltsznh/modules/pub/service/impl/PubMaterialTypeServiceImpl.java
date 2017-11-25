package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubMaterialTypeDao;
import com.ltsznh.modules.pub.entity.PubMaterialTypeEntity;
import com.ltsznh.modules.pub.service.PubMaterialTypeService;



@Service("pubMaterialTypeService")
public class PubMaterialTypeServiceImpl implements PubMaterialTypeService {
	@Autowired
	private PubMaterialTypeDao pubMaterialTypeDao;

	@Override
	public PubMaterialTypeEntity queryObject(String materialTypeCode){
		return pubMaterialTypeDao.queryObject(materialTypeCode);
	}

	@Override
	public List<PubMaterialTypeEntity> queryList(Map<String, Object> map){
		return pubMaterialTypeDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubMaterialTypeDao.queryTotal(map);
	}

	@Override
	public void save(PubMaterialTypeEntity pubMaterialType){
		pubMaterialTypeDao.save(pubMaterialType);
	}

	@Override
	public void update(PubMaterialTypeEntity pubMaterialType){
		pubMaterialTypeDao.update(pubMaterialType);
	}

	@Override
	public void delete(String materialTypeCode,Long userId){
		pubMaterialTypeDao.delete(materialTypeCode,userId);
	}

	@Override
	public void deleteBatch(String[] materialTypeCodes,Long userId){	pubMaterialTypeDao.deleteBatch(materialTypeCodes,userId);}

}
