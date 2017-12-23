package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsWarehouseInventoryDao;
import com.ltsznh.modules.wms.entity.WmsWarehouseInventoryEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseInventoryService;



@Service("wmsWarehouseInventoryService")
public class WmsWarehouseInventoryServiceImpl implements WmsWarehouseInventoryService {
	@Autowired
	private WmsWarehouseInventoryDao wmsWarehouseInventoryDao;

	@Override
	public WmsWarehouseInventoryEntity queryObject(Long wiId){
		return wmsWarehouseInventoryDao.queryObject(wiId);
	}

	@Override
	public List<WmsWarehouseInventoryEntity> queryList(Map<String, Object> map){
		return wmsWarehouseInventoryDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsWarehouseInventoryDao.queryTotal(map);
	}

	@Override
	public void save(WmsWarehouseInventoryEntity wmsWarehouseInventory){
		wmsWarehouseInventoryDao.save(wmsWarehouseInventory);
	}

	@Override
	public void update(WmsWarehouseInventoryEntity wmsWarehouseInventory){
		wmsWarehouseInventoryDao.update(wmsWarehouseInventory);
	}

	@Override
	public void delete(Long wiId,Long userId){
		wmsWarehouseInventoryDao.delete(wiId,userId);
	}

	@Override
	public void deleteBatch(Long[] wiIds,Long userId){	wmsWarehouseInventoryDao.deleteBatch(wiIds,userId);}

}
