package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsTransferOrderAllDao;
import com.ltsznh.modules.wms.entity.WmsTransferOrderAllEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderAllService;



@Service("wmsTransferOrderAllService")
public class WmsTransferOrderAllServiceImpl implements WmsTransferOrderAllService {
	@Autowired
	private WmsTransferOrderAllDao wmsTransferOrderAllDao;

	@Override
	public WmsTransferOrderAllEntity queryObject(String warehouseCode){
		return wmsTransferOrderAllDao.queryObject(warehouseCode);
	}

	@Override
	public List<WmsTransferOrderAllEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderAllDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderAllDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderAllEntity wmsTransferOrderAll){
		wmsTransferOrderAllDao.save(wmsTransferOrderAll);
	}

	@Override
	public void update(WmsTransferOrderAllEntity wmsTransferOrderAll){
		wmsTransferOrderAllDao.update(wmsTransferOrderAll);
	}

	@Override
	public void delete(String warehouseCode,Long userId){
		wmsTransferOrderAllDao.delete(warehouseCode,userId);
	}

	@Override
	public void deleteBatch(String[] warehouseCodes,Long userId){	wmsTransferOrderAllDao.deleteBatch(warehouseCodes,userId);}

}
