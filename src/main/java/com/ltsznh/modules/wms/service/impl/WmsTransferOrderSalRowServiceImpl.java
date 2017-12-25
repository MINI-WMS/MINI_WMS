package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.sal.dao.WmsTransferOrderSalRowDao;
import com.ltsznh.modules.sal.entity.WmsTransferOrderSalRowEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderSalRowService;



@Service("wmsTransferOrderSalRowService")
public class WmsTransferOrderSalRowServiceImpl implements WmsTransferOrderSalRowService {
	@Autowired
	private WmsTransferOrderSalRowDao wmsTransferOrderSalRowDao;

	@Override
	public WmsTransferOrderSalRowEntity queryObject(Long toSalRowId){
		return wmsTransferOrderSalRowDao.queryObject(toSalRowId);
	}

	@Override
	public List<WmsTransferOrderSalRowEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderSalRowDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderSalRowDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderSalRowEntity wmsTransferOrderSalRow){
		wmsTransferOrderSalRowDao.save(wmsTransferOrderSalRow);
	}

	@Override
	public void update(WmsTransferOrderSalRowEntity wmsTransferOrderSalRow){
		wmsTransferOrderSalRowDao.update(wmsTransferOrderSalRow);
	}

	@Override
	public void delete(Long toSalRowId,Long userId){
		wmsTransferOrderSalRowDao.delete(toSalRowId,userId);
	}

	@Override
	public void deleteBatch(Long[] toSalRowIds,Long userId){	wmsTransferOrderSalRowDao.deleteBatch(toSalRowIds,userId);}

}
