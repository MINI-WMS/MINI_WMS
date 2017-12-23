package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsTransferOrderDao;
import com.ltsznh.modules.wms.entity.WmsTransferOrderEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderService;



@Service("wmsTransferOrderService")
public class WmsTransferOrderServiceImpl implements WmsTransferOrderService {
	@Autowired
	private WmsTransferOrderDao wmsTransferOrderDao;

	@Override
	public WmsTransferOrderEntity queryObject(Long toId){
		return wmsTransferOrderDao.queryObject(toId);
	}

	@Override
	public List<WmsTransferOrderEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderEntity wmsTransferOrder){
		wmsTransferOrderDao.save(wmsTransferOrder);
	}

	@Override
	public void update(WmsTransferOrderEntity wmsTransferOrder){
		wmsTransferOrderDao.update(wmsTransferOrder);
	}

	@Override
	public void delete(Long toId,Long userId){
		wmsTransferOrderDao.delete(toId,userId);
	}

	@Override
	public void deleteBatch(Long[] toIds,Long userId){	wmsTransferOrderDao.deleteBatch(toIds,userId);}

}
