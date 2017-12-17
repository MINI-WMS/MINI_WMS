package com.ltsznh.modules.pur.service;

import com.ltsznh.modules.pur.entity.PurSupplierTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商类型
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
public interface PurSupplierTypeService {
	
	PurSupplierTypeEntity queryObject(String supplierTypeCode);
	
	List<PurSupplierTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PurSupplierTypeEntity purSupplierType);
	
	void update(PurSupplierTypeEntity purSupplierType);
	
	void delete(String supplierTypeCode,Long userId);
	
	void deleteBatch(String[] supplierTypeCodes,Long userId);
}
