package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsTransferOrderRowEntity;

import java.util.List;
import java.util.Map;

/**
 * 仓库入出库单明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:04
 */
public interface WmsTransferOrderRowService {
	
	WmsTransferOrderRowEntity queryObject(String materialCode);
	
	List<WmsTransferOrderRowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderRowEntity wmsTransferOrderRow);
	
	void update(WmsTransferOrderRowEntity wmsTransferOrderRow);
	
	void delete(String materialCode,Long userId);
	
	void deleteBatch(String[] materialCodes,Long userId);
}
