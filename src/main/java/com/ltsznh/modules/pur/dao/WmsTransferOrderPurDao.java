package com.ltsznh.modules.pur.dao;

import com.ltsznh.modules.sys.dao.BaseDao;
import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;

import java.util.List;
import java.util.Map;

/**
 * 采购入库单
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:02:57
 */
public interface WmsTransferOrderPurDao extends BaseDao<WmsTransferOrderPurEntity> {
    List<WmsTransferOrderPurEntity> queryLatest(Map<String, Object> map);
	
}
