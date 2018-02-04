package com.ltsznh.modules.pur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pur.dao.PurSupplierDao;
import com.ltsznh.modules.pur.entity.PurSupplierEntity;
import com.ltsznh.modules.pur.service.PurSupplierService;



@Service("purSupplierService")
public class PurSupplierServiceImpl implements PurSupplierService {
	@Autowired
	private PurSupplierDao purSupplierDao;

	@Override
	public PurSupplierEntity queryObject(String supplierCode){
		return purSupplierDao.queryObject(supplierCode);
	}

	@Override
	public List<PurSupplierEntity> queryList(Map<String, Object> map){
		return purSupplierDao.queryList(map);
	}

	@Override
	public List<PurSupplierEntity> querySelect2(Map<String, Object> map) {
		return purSupplierDao.querySelect2(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return purSupplierDao.queryTotal(map);
	}

	@Override
	public void save(PurSupplierEntity purSupplier){
		purSupplierDao.save(purSupplier);
	}

	@Override
	public void update(PurSupplierEntity purSupplier){
		purSupplierDao.update(purSupplier);
	}

	@Override
	public void delete(String supplierCode,Long userId){
		purSupplierDao.delete(supplierCode,userId);
	}

	@Override
	public void deleteBatch(String[] supplierCodes,Long userId){	purSupplierDao.deleteBatch(supplierCodes,userId);}

}
