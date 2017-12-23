package com.ltsznh.modules.sal.service;

import com.ltsznh.modules.sal.entity.WmsTransferOrderSalRowEntity;

import java.util.List;
import java.util.Map;

/**
 * 销售出库明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
public interface WmsTransferOrderSalRowService {
	
	WmsTransferOrderSalRowEntity queryObject(Long toSalRowId);
	
	List<WmsTransferOrderSalRowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderSalRowEntity wmsTransferOrderSalRow);
	
	void update(WmsTransferOrderSalRowEntity wmsTransferOrderSalRow);
	
	void delete(Long toSalRowId,Long userId);
	
	void deleteBatch(Long[] toSalRowIds,Long userId);
}
