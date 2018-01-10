package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-08 15:36:01
 */
public interface PubBrandService {
	
	PubBrandEntity queryObject(Long brandId);
	
	List<PubBrandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubBrandEntity pubBrand);
	
	void update(PubBrandEntity pubBrand);
	
	void delete(Long brandId,Long userId);
	
	void deleteBatch(Long[] brandIds,Long userId);
}
