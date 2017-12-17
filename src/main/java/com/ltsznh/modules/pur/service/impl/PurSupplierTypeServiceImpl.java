package com.ltsznh.modules.pur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pur.dao.PurSupplierTypeDao;
import com.ltsznh.modules.pur.entity.PurSupplierTypeEntity;
import com.ltsznh.modules.pur.service.PurSupplierTypeService;



@Service("purSupplierTypeService")
public class PurSupplierTypeServiceImpl implements PurSupplierTypeService {
	@Autowired
	private PurSupplierTypeDao purSupplierTypeDao;

	@Override
	public PurSupplierTypeEntity queryObject(String supplierTypeCode){
		return purSupplierTypeDao.queryObject(supplierTypeCode);
	}

	@Override
	public List<PurSupplierTypeEntity> queryList(Map<String, Object> map){
		return purSupplierTypeDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return purSupplierTypeDao.queryTotal(map);
	}

	@Override
	public void save(PurSupplierTypeEntity purSupplierType){
		purSupplierTypeDao.save(purSupplierType);
	}

	@Override
	public void update(PurSupplierTypeEntity purSupplierType){
		purSupplierTypeDao.update(purSupplierType);
	}

	@Override
	public void delete(String supplierTypeCode,Long userId){
		purSupplierTypeDao.delete(supplierTypeCode,userId);
	}

	@Override
	public void deleteBatch(String[] supplierTypeCodes,Long userId){	purSupplierTypeDao.deleteBatch(supplierTypeCodes,userId);}

}
