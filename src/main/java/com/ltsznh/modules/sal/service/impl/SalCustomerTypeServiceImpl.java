package com.ltsznh.modules.sal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.sal.dao.SalCustomerTypeDao;
import com.ltsznh.modules.sal.entity.SalCustomerTypeEntity;
import com.ltsznh.modules.sal.service.SalCustomerTypeService;



@Service("salCustomerTypeService")
public class SalCustomerTypeServiceImpl implements SalCustomerTypeService {
	@Autowired
	private SalCustomerTypeDao salCustomerTypeDao;

	@Override
	public SalCustomerTypeEntity queryObject(String customerTypeCode){
		return salCustomerTypeDao.queryObject(customerTypeCode);
	}

	@Override
	public List<SalCustomerTypeEntity> queryList(Map<String, Object> map){
		return salCustomerTypeDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return salCustomerTypeDao.queryTotal(map);
	}

	@Override
	public void save(SalCustomerTypeEntity salCustomerType){
		salCustomerTypeDao.save(salCustomerType);
	}

	@Override
	public void update(SalCustomerTypeEntity salCustomerType){
		salCustomerTypeDao.update(salCustomerType);
	}

	@Override
	public void delete(String customerTypeCode,Long userId){
		salCustomerTypeDao.delete(customerTypeCode,userId);
	}

	@Override
	public void deleteBatch(String[] customerTypeCodes,Long userId){	salCustomerTypeDao.deleteBatch(customerTypeCodes,userId);}

}
