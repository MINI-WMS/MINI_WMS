package com.ltsznh.modules.sal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.sal.dao.WmsTransferOrderSalDao;
import com.ltsznh.modules.sal.entity.WmsTransferOrderSalEntity;
import com.ltsznh.modules.sal.service.WmsTransferOrderSalService;



@Service("wmsTransferOrderSalService")
public class WmsTransferOrderSalServiceImpl implements WmsTransferOrderSalService {
	@Autowired
	private WmsTransferOrderSalDao wmsTransferOrderSalDao;

	@Override
	public WmsTransferOrderSalEntity queryObject(Long toSalId){
		return wmsTransferOrderSalDao.queryObject(toSalId);
	}

	@Override
	public List<WmsTransferOrderSalEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderSalDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderSalDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderSalEntity wmsTransferOrderSal){
		wmsTransferOrderSalDao.save(wmsTransferOrderSal);
	}

	@Override
	public void update(WmsTransferOrderSalEntity wmsTransferOrderSal){
		wmsTransferOrderSalDao.update(wmsTransferOrderSal);
	}

	@Override
	public void delete(Long toSalId,Long userId){
		wmsTransferOrderSalDao.delete(toSalId,userId);
	}

	@Override
	public void deleteBatch(Long[] toSalIds,Long userId){	wmsTransferOrderSalDao.deleteBatch(toSalIds,userId);}

}
