package com.ltsznh.modules.sal.service;

import com.ltsznh.modules.sal.entity.SalCustomerMaterialPriceEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户物料价格
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:26
 */
public interface SalCustomerMaterialPriceService {
	
	SalCustomerMaterialPriceEntity queryObject(Long mcpId);
	
	List<SalCustomerMaterialPriceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SalCustomerMaterialPriceEntity salCustomerMaterialPrice);
	
	void update(SalCustomerMaterialPriceEntity salCustomerMaterialPrice);
	
	void delete(Long mcpId,Long userId);
	
	void deleteBatch(Long[] mcpIds,Long userId);
}
