package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsWarehouseUserDao;
import com.ltsznh.modules.wms.entity.WmsWarehouseUserEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseUserService;



@Service("wmsWarehouseUserService")
public class WmsWarehouseUserServiceImpl implements WmsWarehouseUserService {
	@Autowired
	private WmsWarehouseUserDao wmsWarehouseUserDao;

	@Override
	public WmsWarehouseUserEntity queryObject(Long wmsUserId){
		return wmsWarehouseUserDao.queryObject(wmsUserId);
	}

	@Override
	public List<WmsWarehouseUserEntity> queryList(Map<String, Object> map){
		return wmsWarehouseUserDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsWarehouseUserDao.queryTotal(map);
	}

	@Override
	public void save(WmsWarehouseUserEntity wmsWarehouseUser){
		wmsWarehouseUserDao.save(wmsWarehouseUser);
	}

	@Override
	public void delete(Long wmsUserId,Long modifierId){
		wmsWarehouseUserDao.delete(wmsUserId,modifierId);
	}


	@Override
	public List<String> queryWarehouseCodeList(Long userId){return wmsWarehouseUserDao.queryWarehouseCodeList(userId);}

}
