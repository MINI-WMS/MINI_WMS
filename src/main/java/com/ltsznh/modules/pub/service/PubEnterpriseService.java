package com.ltsznh.modules.pub.service;

import com.ltsznh.modules.pub.entity.PubEnterpriseEntity;

import java.util.List;
import java.util.Map;

/**
 * 企业
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public interface PubEnterpriseService {
	
	PubEnterpriseEntity queryObject(String enterpriseCode);
	
	List<PubEnterpriseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PubEnterpriseEntity pubEnterprise);
	
	void update(PubEnterpriseEntity pubEnterprise);
	
	void delete(String enterpriseCode,Long userId);
	
	void deleteBatch(String[] enterpriseCodes,Long userId);
}
