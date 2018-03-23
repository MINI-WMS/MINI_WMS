package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsTransferOrderAllEntity;

import java.util.List;
import java.util.Map;

/**
 * 仓库库存
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-03-19 15:19:41
 */
public interface WmsTransferOrderAllService {
	
	WmsTransferOrderAllEntity queryObject(String warehouseCode);
	
	List<WmsTransferOrderAllEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderAllEntity wmsTransferOrderAll);
	
	void update(WmsTransferOrderAllEntity wmsTransferOrderAll);
	
	void delete(String warehouseCode,Long userId);
	
	void deleteBatch(String[] warehouseCodes,Long userId);
}
