package com.ltsznh.modules.pur.service;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurRowEntity;

import java.util.List;
import java.util.Map;

/**
 * 采购入库单明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
public interface WmsTransferOrderPurRowService {
	
	WmsTransferOrderPurRowEntity queryObject(Long toPurRowId);
	
	List<WmsTransferOrderPurRowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderPurRowEntity wmsTransferOrderPurRow);
	
	void update(WmsTransferOrderPurRowEntity wmsTransferOrderPurRow);
	
	void delete(Long toPurRowId,Long userId);
	
	void deleteBatch(Long[] toPurRowIds,Long userId);
}
