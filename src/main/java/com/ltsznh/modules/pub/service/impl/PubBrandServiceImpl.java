package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubBrandDao;
import com.ltsznh.modules.pub.entity.PubBrandEntity;
import com.ltsznh.modules.pub.service.PubBrandService;



@Service("pubBrandService")
public class PubBrandServiceImpl implements PubBrandService {
	@Autowired
	private PubBrandDao pubBrandDao;

	@Override
	public PubBrandEntity queryObject(Long brandId){
		return pubBrandDao.queryObject(brandId);
	}

	@Override
	public List<PubBrandEntity> queryList(Map<String, Object> map){
		return pubBrandDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubBrandDao.queryTotal(map);
	}

	@Override
	public void save(PubBrandEntity pubBrand){
		pubBrandDao.save(pubBrand);
	}

	@Override
	public void update(PubBrandEntity pubBrand){
		pubBrandDao.update(pubBrand);
	}

	@Override
	public void delete(Long brandId,Long userId){
		pubBrandDao.delete(brandId,userId);
	}

	@Override
	public void deleteBatch(Long[] brandIds,Long userId){	pubBrandDao.deleteBatch(brandIds,userId);}

}
