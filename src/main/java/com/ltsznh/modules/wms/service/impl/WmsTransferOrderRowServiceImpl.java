package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsTransferOrderRowDao;
import com.ltsznh.modules.wms.entity.WmsTransferOrderRowEntity;
import com.ltsznh.modules.wms.service.WmsTransferOrderRowService;



@Service("wmsTransferOrderRowService")
public class WmsTransferOrderRowServiceImpl implements WmsTransferOrderRowService {
	@Autowired
	private WmsTransferOrderRowDao wmsTransferOrderRowDao;

	@Override
	public WmsTransferOrderRowEntity queryObject(String materialCode){
		return wmsTransferOrderRowDao.queryObject(materialCode);
	}

	@Override
	public List<WmsTransferOrderRowEntity> queryList(Map<String, Object> map){
		return wmsTransferOrderRowDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsTransferOrderRowDao.queryTotal(map);
	}

	@Override
	public void save(WmsTransferOrderRowEntity wmsTransferOrderRow){
		wmsTransferOrderRowDao.save(wmsTransferOrderRow);
	}

	@Override
	public void update(WmsTransferOrderRowEntity wmsTransferOrderRow){
		wmsTransferOrderRowDao.update(wmsTransferOrderRow);
	}

	@Override
	public void delete(String materialCode,Long userId){
		wmsTransferOrderRowDao.delete(materialCode,userId);
	}

	@Override
	public void deleteBatch(String[] materialCodes,Long userId){	wmsTransferOrderRowDao.deleteBatch(materialCodes,userId);}

}
