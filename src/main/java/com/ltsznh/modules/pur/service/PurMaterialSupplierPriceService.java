package com.ltsznh.modules.pur.service;

import com.ltsznh.modules.pur.entity.PurMaterialSupplierPriceEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料供应商价格
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
public interface PurMaterialSupplierPriceService {
	
	PurMaterialSupplierPriceEntity queryObject(Long mspId);
	
	List<PurMaterialSupplierPriceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PurMaterialSupplierPriceEntity purMaterialSupplierPrice);
	
	void update(PurMaterialSupplierPriceEntity purMaterialSupplierPrice);
	
	void delete(Long mspId,Long userId);
	
	void deleteBatch(Long[] mspIds,Long userId);
}
