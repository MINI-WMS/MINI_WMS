package com.ltsznh.modules.sal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.sal.dao.SalCustomerDao;
import com.ltsznh.modules.sal.entity.SalCustomerEntity;
import com.ltsznh.modules.sal.service.SalCustomerService;



@Service("salCustomerService")
public class SalCustomerServiceImpl implements SalCustomerService {
	@Autowired
	private SalCustomerDao salCustomerDao;

	@Override
	public SalCustomerEntity queryObject(String customerCode){
		return salCustomerDao.queryObject(customerCode);
	}

	@Override
	public List<SalCustomerEntity> queryList(Map<String, Object> map){
		return salCustomerDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return salCustomerDao.queryTotal(map);
	}

	@Override
	public void save(SalCustomerEntity salCustomer){
		salCustomerDao.save(salCustomer);
	}

	@Override
	public void update(SalCustomerEntity salCustomer){
		salCustomerDao.update(salCustomer);
	}

	@Override
	public void delete(String customerCode,Long userId){
		salCustomerDao.delete(customerCode,userId);
	}

	@Override
	public void deleteBatch(String[] customerCodes,Long userId){	salCustomerDao.deleteBatch(customerCodes,userId);}

}
