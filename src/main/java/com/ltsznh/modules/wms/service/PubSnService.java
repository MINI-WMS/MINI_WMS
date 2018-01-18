package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.PubSnEntity;

import java.util.List;
import java.util.Map;

/**
 * 单据日期
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-12 11:28:32
 */
public interface PubSnService {
	
	PubSnEntity queryObject(Long snId);
	
	List<PubSnEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubSnEntity pubSn);
	
	void update(PubSnEntity pubSn);
	
	void delete(Long snId,Long userId);
	
	void deleteBatch(Long[] snIds,Long userId);

	void DayEndClearing(Long[] snIds,Long userId);

	void DayEndClearing2(Long[] snIds,Long userId);
}
