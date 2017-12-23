package com.ltsznh.modules.pur.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pur.dao.WmsTransferOrderPurRowDao;
import com.ltsznh.modules.pur.entity.WmsTransferOrderPurRowEntity;
import com.ltsznh.modules.pur.service.WmsTransferOrderPurRowService;



@Service("wmsTransferOrderPurRowService")
public class WmsTransferOrderPurRowServiceImpl implements WmsTransferOrderPurRowService {
	@Autowired
	private WmsTransferOrderPurRowDao wmsTransferOrderPurRowDao;

	@Override
	public WmsTransferOrderPurRowEntity queryObject(Long toPurRowId){
		return wmsTransferOrderPurRowDao.queryObject(toPurRowId);
	}

	@Override
	public List<WmsTransferOrderPurRowEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderPurRowDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderPurRowDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderPurRowEntity wmsTransferOrderPurRow){
		wmsTransferOrderPurRowDao.save(wmsTransferOrderPurRow);
	}

	@Override
	public void update(WmsTransferOrderPurRowEntity wmsTransferOrderPurRow){
		wmsTransferOrderPurRowDao.update(wmsTransferOrderPurRow);
	}

	@Override
	public void delete(Long toPurRowId,Long userId){
		wmsTransferOrderPurRowDao.delete(toPurRowId,userId);
	}

	@Override
	public void deleteBatch(Long[] toPurRowIds,Long userId){	wmsTransferOrderPurRowDao.deleteBatch(toPurRowIds,userId);}

}
