package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsWarehouseInventoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 仓库库存
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:55
 */
public interface WmsWarehouseInventoryService {
	
	WmsWarehouseInventoryEntity queryObject(Long wiId);
	
	List<WmsWarehouseInventoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsWarehouseInventoryEntity wmsWarehouseInventory);
	
	void update(WmsWarehouseInventoryEntity wmsWarehouseInventory);
	
	void delete(Long wiId,Long userId);
	
	void deleteBatch(Long[] wiIds,Long userId);
}
