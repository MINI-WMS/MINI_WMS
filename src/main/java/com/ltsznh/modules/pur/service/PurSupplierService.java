package com.ltsznh.modules.pur.service;

import com.ltsznh.modules.pur.entity.PurSupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
public interface PurSupplierService {
	
	PurSupplierEntity queryObject(String supplierCode);
	
	List<PurSupplierEntity> queryList(Map<String, Object> map);

	List<PurSupplierEntity> querySelect2(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
	
	void save(PurSupplierEntity purSupplier);
	
	void update(PurSupplierEntity purSupplier);
	
	void delete(String supplierCode,Long userId);
	
	void deleteBatch(String[] supplierCodes,Long userId);
}
