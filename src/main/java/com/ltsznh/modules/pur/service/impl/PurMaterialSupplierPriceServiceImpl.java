package com.ltsznh.modules.pur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pur.dao.PurMaterialSupplierPriceDao;
import com.ltsznh.modules.pur.entity.PurMaterialSupplierPriceEntity;
import com.ltsznh.modules.pur.service.PurMaterialSupplierPriceService;



@Service("purMaterialSupplierPriceService")
public class PurMaterialSupplierPriceServiceImpl implements PurMaterialSupplierPriceService {
	@Autowired
	private PurMaterialSupplierPriceDao purMaterialSupplierPriceDao;

	@Override
	public PurMaterialSupplierPriceEntity queryObject(Long mspId){
		return purMaterialSupplierPriceDao.queryObject(mspId);
	}

	@Override
	public List<PurMaterialSupplierPriceEntity> queryList(Map<String, Object> map){
		return purMaterialSupplierPriceDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return purMaterialSupplierPriceDao.queryTotal(map);
	}

	@Override
	public void save(PurMaterialSupplierPriceEntity purMaterialSupplierPrice){
		purMaterialSupplierPriceDao.save(purMaterialSupplierPrice);
	}

	@Override
	public void update(PurMaterialSupplierPriceEntity purMaterialSupplierPrice){
		purMaterialSupplierPriceDao.update(purMaterialSupplierPrice);
	}

	@Override
	public void delete(Long mspId,Long userId){
		purMaterialSupplierPriceDao.delete(mspId,userId);
	}

	@Override
	public void deleteBatch(Long[] mspIds,Long userId){	purMaterialSupplierPriceDao.deleteBatch(mspIds,userId);}

}
