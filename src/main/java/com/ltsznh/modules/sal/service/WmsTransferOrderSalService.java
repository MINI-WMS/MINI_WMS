package com.ltsznh.modules.sal.service;

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;

import java.util.List;
import java.util.Map;

/**
 * 销售出库
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
public interface WmsTransferOrderSalService {
	
	WmsTransferOrderSalEntity queryObject(Long toSalId);
	
	List<WmsTransferOrderSalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderSalEntity wmsTransferOrderSal);
	
	void update(WmsTransferOrderSalEntity wmsTransferOrderSal);
	
	void delete(Long toSalId,Long userId);
	
	void deleteBatch(Long[] toSalIds,Long userId);
}