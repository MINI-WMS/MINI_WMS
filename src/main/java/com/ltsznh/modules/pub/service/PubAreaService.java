package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 地区
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public interface PubAreaService {
	
	PubAreaEntity queryObject(String areaCode);
	
	List<PubAreaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubAreaEntity pubArea);
	
	void update(PubAreaEntity pubArea);
	
	void delete(String areaCode,Long userId);
	
	void deleteBatch(String[] areaCodes,Long userId);
}
