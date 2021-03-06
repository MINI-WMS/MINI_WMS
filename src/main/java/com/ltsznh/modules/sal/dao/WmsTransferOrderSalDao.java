package com.ltsznh.modules.sal.dao;

import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.sys.dao.BaseDao;
import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;

import java.util.List;
import java.util.Map;

/**
 * 销售出库
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
public interface WmsTransferOrderSalDao extends BaseDao<WmsTransferOrderSalEntity> {
    List<WmsTransferOrderSalEntity> queryLatest(Map<String, Object> map);

}
