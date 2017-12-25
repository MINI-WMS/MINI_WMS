package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsTransferOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 仓库入出库单
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:03
 */
public interface WmsTransferOrderService {
	
	WmsTransferOrderEntity queryObject(String warehouseCode);
	
	List<WmsTransferOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderEntity wmsTransferOrder);
	
	void update(WmsTransferOrderEntity wmsTransferOrder);
	
	void delete(String warehouseCode,Long userId);
	
	void deleteBatch(String[] warehouseCodes,Long userId);
}
