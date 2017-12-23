package com.ltsznh.modules.sal.service;

import com.ltsznh.modules.sal.entity.SalCustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:26
 */
public interface SalCustomerService {
	
	SalCustomerEntity queryObject(String customerCode);
	
	List<SalCustomerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SalCustomerEntity salCustomer);
	
	void update(SalCustomerEntity salCustomer);
	
	void delete(String customerCode,Long userId);
	
	void deleteBatch(String[] customerCodes,Long userId);
}
