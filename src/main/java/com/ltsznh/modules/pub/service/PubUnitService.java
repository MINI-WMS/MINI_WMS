package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubUnitEntity;

import java.util.List;
import java.util.Map;

/**
 * 单位
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public interface PubUnitService {
	
	PubUnitEntity queryObject(String unitCode);
	
	List<PubUnitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubUnitEntity pubUnit);
	
	void update(PubUnitEntity pubUnit);
	
	void delete(String unitCode,Long userId);
	
	void deleteBatch(String[] unitCodes,Long userId);
}
