package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsTransferOrderSnEntity;

import java.util.List;
import java.util.Map;

/**
 * 物料SN明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
public interface WmsTransferOrderSnService {
	
	WmsTransferOrderSnEntity queryObject(Long toSnId);
	
	List<WmsTransferOrderSnEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsTransferOrderSnEntity wmsTransferOrderSn);
	
	void update(WmsTransferOrderSnEntity wmsTransferOrderSn);
	
	void delete(Long toSnId,Long userId);
	
	void deleteBatch(Long[] toSnIds,Long userId);
}
