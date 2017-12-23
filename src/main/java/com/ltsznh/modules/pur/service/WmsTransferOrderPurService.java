package com.ltsznh.modules.pur.service;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;

import java.util.List;
import java.util.Map;

/**
 * 采购入库单
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
public interface WmsTransferOrderPurService {
	
	WmsTransferOrderPurEntity queryObject(Long toPurId);
	
	List<WmsTransferOrderPurEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderPurEntity wmsTransferOrderPur);
	
	void update(WmsTransferOrderPurEntity wmsTransferOrderPur);
	
	void delete(Long toPurId,Long userId);
	
	void deleteBatch(Long[] toPurIds,Long userId);
}
