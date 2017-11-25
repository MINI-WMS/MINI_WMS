package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubMaterialTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料类型
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public interface PubMaterialTypeService {
	
	PubMaterialTypeEntity queryObject(String materialTypeCode);
	
	List<PubMaterialTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubMaterialTypeEntity pubMaterialType);
	
	void update(PubMaterialTypeEntity pubMaterialType);
	
	void delete(String materialTypeCode,Long userId);
	
	void deleteBatch(String[] materialTypeCodes,Long userId);
}
