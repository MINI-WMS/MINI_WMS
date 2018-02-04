package com.ltsznh.modules.pur.dao;

import com.ltsznh.modules.sys.dao.BaseDao;
import com.ltsznh.modules.pur.entity.PurSupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
public interface PurSupplierDao extends BaseDao<PurSupplierEntity> {
    List<PurSupplierEntity> querySelect2(Map<String, Object> map);
}
