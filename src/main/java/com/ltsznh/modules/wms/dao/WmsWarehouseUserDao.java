package com.ltsznh.modules.wms.dao;

import com.ltsznh.modules.sys.dao.BaseDao;
import com.ltsznh.modules.wms.entity.WmsWarehouseUserEntity;

import java.util.List;

/**
 * 用户仓库权限管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
public interface WmsWarehouseUserDao extends BaseDao<WmsWarehouseUserEntity> {

	List<String> queryWarehouseCodeList(Long userId);
}
