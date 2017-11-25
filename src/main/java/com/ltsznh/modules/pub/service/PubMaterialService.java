package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubMaterialEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public interface PubMaterialService {
	
	PubMaterialEntity queryObject(String materialCode);
	
	List<PubMaterialEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubMaterialEntity pubMaterial);
	
	void update(PubMaterialEntity pubMaterial);
	
	void delete(String materialCode,Long userId);
	
	void deleteBatch(String[] materialCodes,Long userId);
}
