package com.ltsznh.modules.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.wms.dao.WmsMoveTypeDao;
import com.ltsznh.modules.wms.entity.WmsMoveTypeEntity;
import com.ltsznh.modules.wms.service.WmsMoveTypeService;



@Service("wmsMoveTypeService")
public class WmsMoveTypeServiceImpl implements WmsMoveTypeService {
	@Autowired
	private WmsMoveTypeDao wmsMoveTypeDao;

	@Override
	public WmsMoveTypeEntity queryObject(String moveTypeCode){
		return wmsMoveTypeDao.queryObject(moveTypeCode);
	}

	@Override
	public List<WmsMoveTypeEntity> queryList(Map<String, Object> map){
		return wmsMoveTypeDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return wmsMoveTypeDao.queryTotal(map);
	}

	@Override
	public void save(WmsMoveTypeEntity wmsMoveType){
		wmsMoveTypeDao.save(wmsMoveType);
	}

	@Override
	public void update(WmsMoveTypeEntity wmsMoveType){
		wmsMoveTypeDao.update(wmsMoveType);
	}

	@Override
	public void delete(String moveTypeCode,Long userId){
		wmsMoveTypeDao.delete(moveTypeCode,userId);
	}

	@Override
	public void deleteBatch(String[] moveTypeCodes,Long userId){	wmsMoveTypeDao.deleteBatch(moveTypeCodes,userId);}

}
