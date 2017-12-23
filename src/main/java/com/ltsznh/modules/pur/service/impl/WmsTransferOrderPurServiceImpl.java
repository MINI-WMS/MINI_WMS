package com.ltsznh.modules.pur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pur.dao.WmsTransferOrderPurDao;
import com.ltsznh.modules.pur.entity.WmsTransferOrderPurEntity;
import com.ltsznh.modules.pur.service.WmsTransferOrderPurService;



@Service("wmsTransferOrderPurService")
public class WmsTransferOrderPurServiceImpl implements WmsTransferOrderPurService {
	@Autowired
	private WmsTransferOrderPurDao wmsTransferOrderPurDao;

	@Override
	public WmsTransferOrderPurEntity queryObject(Long toPurId){
		return wmsTransferOrderPurDao.queryObject(toPurId);
	}

	@Override
	public List<WmsTransferOrderPurEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderPurDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderPurDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderPurEntity wmsTransferOrderPur){
		wmsTransferOrderPurDao.save(wmsTransferOrderPur);
	}

	@Override
	public void update(WmsTransferOrderPurEntity wmsTransferOrderPur){
		wmsTransferOrderPurDao.update(wmsTransferOrderPur);
	}

	@Override
	public void delete(Long toPurId,Long userId){
		wmsTransferOrderPurDao.delete(toPurId,userId);
	}

	@Override
	public void deleteBatch(Long[] toPurIds,Long userId){	wmsTransferOrderPurDao.deleteBatch(toPurIds,userId);}

}
