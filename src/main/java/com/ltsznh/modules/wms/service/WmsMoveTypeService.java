package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsMoveTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 库存移动类型
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:53
 */
public interface WmsMoveTypeService {
	
	WmsMoveTypeEntity queryObject(String moveTypeCode);
	
	List<WmsMoveTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsMoveTypeEntity wmsMoveType);
	
	void update(WmsMoveTypeEntity wmsMoveType);
	
	void delete(String moveTypeCode,Long userId);
	
	void deleteBatch(String[] moveTypeCodes,Long userId);
}
