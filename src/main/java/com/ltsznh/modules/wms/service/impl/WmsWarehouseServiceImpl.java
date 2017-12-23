package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsWarehouseDao;
import com.ltsznh.modules.wms.entity.WmsWarehouseEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseService;



@Service("wmsWarehouseService")
public class WmsWarehouseServiceImpl implements WmsWarehouseService {
	@Autowired
	private WmsWarehouseDao wmsWarehouseDao;

	@Override
	public WmsWarehouseEntity queryObject(String warehouseCode){
		return wmsWarehouseDao.queryObject(warehouseCode);
	}

	@Override
	public List<WmsWarehouseEntity> queryList(Map<String, Object> map){
		return wmsWarehouseDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsWarehouseDao.queryTotal(map);
	}

	@Override
	public void save(WmsWarehouseEntity wmsWarehouse){
		wmsWarehouseDao.save(wmsWarehouse);
	}

	@Override
	public void update(WmsWarehouseEntity wmsWarehouse){
		wmsWarehouseDao.update(wmsWarehouse);
	}

	@Override
	public void delete(String warehouseCode,Long userId){
		wmsWarehouseDao.delete(warehouseCode,userId);
	}

	@Override
	public void deleteBatch(String[] warehouseCodes,Long userId){	wmsWarehouseDao.deleteBatch(warehouseCodes,userId);}

}
