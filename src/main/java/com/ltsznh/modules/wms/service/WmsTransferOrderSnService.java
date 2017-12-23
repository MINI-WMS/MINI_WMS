package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsTransferOrderSnEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料SN明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:53
 */
public interface WmsTransferOrderSnService {
	
	WmsTransferOrderSnEntity queryObject(String materialCode);
	
	List<WmsTransferOrderSnEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderSnEntity wmsTransferOrderSn);
	
	void update(WmsTransferOrderSnEntity wmsTransferOrderSn);
	
	void delete(String materialCode,Long userId);
	
	void deleteBatch(String[] materialCodes,Long userId);
}
