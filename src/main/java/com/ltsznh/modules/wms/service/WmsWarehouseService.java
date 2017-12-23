package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsWarehouseEntity;

import java.util.List;
import java.util.Map;

/**
 * 仓库
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
public interface WmsWarehouseService {
	
	WmsWarehouseEntity queryObject(String warehouseCode);
	
	List<WmsWarehouseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsWarehouseEntity wmsWarehouse);
	
	void update(WmsWarehouseEntity wmsWarehouse);
	
	void delete(String warehouseCode,Long userId);
	
	void deleteBatch(String[] warehouseCodes,Long userId);
}
