package com.ltsznh.modules.wms.service;

import com.ltsznh.modules.wms.entity.WmsWarehouseUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户仓库权限管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
public interface WmsWarehouseUserService {
	
	WmsWarehouseUserEntity queryObject(Long wmsUserId);
	
	List<WmsWarehouseUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WmsWarehouseUserEntity wmsWarehouseUser);

	
	void delete(Long wmsUserId,Long modifierId);


	List<String> queryWarehouseCodeList(Long userId);
}
