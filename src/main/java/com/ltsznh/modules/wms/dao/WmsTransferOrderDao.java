package com.ltsznh.modules.wms.dao;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.sys.dao.BaseDao;
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
public interface WmsTransferOrderDao extends BaseDao<WmsTransferOrderEntity> {
    List<WmsTransferOrderEntity> queryLatest(Map<String, Object> map);
}
