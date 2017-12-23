package com.ltsznh.modules.sal.service;

import com.ltsznh.modules.sal.entity.SalCustomerTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户类型
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
public interface SalCustomerTypeService {
	
	SalCustomerTypeEntity queryObject(String customerTypeCode);
	
	List<SalCustomerTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SalCustomerTypeEntity salCustomerType);
	
	void update(SalCustomerTypeEntity salCustomerType);
	
	void delete(String customerTypeCode,Long userId);
	
	void deleteBatch(String[] customerTypeCodes,Long userId);
}
