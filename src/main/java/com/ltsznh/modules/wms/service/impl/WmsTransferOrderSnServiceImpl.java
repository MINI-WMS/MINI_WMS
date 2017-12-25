package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsTransferOrderSnDao;
import com.ltsznh.modules.wms.entity.WmsTransferOrderSnEntity;
import com.ltsznh.modules.sal.service.WmsTransferOrderSnService;



@Service("wmsTransferOrderSnService")
public class WmsTransferOrderSnServiceImpl implements WmsTransferOrderSnService {
	@Autowired
	private WmsTransferOrderSnDao wmsTransferOrderSnDao;

	@Override
	public WmsTransferOrderSnEntity queryObject(Long toSnId){
		return wmsTransferOrderSnDao.queryObject(toSnId);
	}

	@Override
	public List<WmsTransferOrderSnEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderSnDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderSnDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderSnEntity wmsTransferOrderSn){
		wmsTransferOrderSnDao.save(wmsTransferOrderSn);
	}

	@Override
	public void update(WmsTransferOrderSnEntity wmsTransferOrderSn){
		wmsTransferOrderSnDao.update(wmsTransferOrderSn);
	}

	@Override
	public void delete(Long toSnId,Long userId){
		wmsTransferOrderSnDao.delete(toSnId,userId);
	}

	@Override
	public void deleteBatch(Long[] toSnIds,Long userId){	wmsTransferOrderSnDao.deleteBatch(toSnIds,userId);}

}
