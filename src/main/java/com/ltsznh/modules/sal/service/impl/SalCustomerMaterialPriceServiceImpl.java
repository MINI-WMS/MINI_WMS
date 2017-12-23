package com.ltsznh.modules.sal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.sal.dao.SalCustomerMaterialPriceDao;
import com.ltsznh.modules.sal.entity.SalCustomerMaterialPriceEntity;
import com.ltsznh.modules.sal.service.SalCustomerMaterialPriceService;



@Service("salCustomerMaterialPriceService")
public class SalCustomerMaterialPriceServiceImpl implements SalCustomerMaterialPriceService {
	@Autowired
	private SalCustomerMaterialPriceDao salCustomerMaterialPriceDao;

	@Override
	public SalCustomerMaterialPriceEntity queryObject(Long mcpId){
		return salCustomerMaterialPriceDao.queryObject(mcpId);
	}

	@Override
	public List<SalCustomerMaterialPriceEntity> queryList(Map<String, Object> map){
		return salCustomerMaterialPriceDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return salCustomerMaterialPriceDao.queryTotal(map);
	}

	@Override
	public void save(SalCustomerMaterialPriceEntity salCustomerMaterialPrice){
		salCustomerMaterialPriceDao.save(salCustomerMaterialPrice);
	}

	@Override
	public void update(SalCustomerMaterialPriceEntity salCustomerMaterialPrice){
		salCustomerMaterialPriceDao.update(salCustomerMaterialPrice);
	}

	@Override
	public void delete(Long mcpId,Long userId){
		salCustomerMaterialPriceDao.delete(mcpId,userId);
	}

	@Override
	public void deleteBatch(Long[] mcpIds,Long userId){	salCustomerMaterialPriceDao.deleteBatch(mcpIds,userId);}

}
