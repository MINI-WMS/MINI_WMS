package com.ltsznh.modules.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ltsznh.modules.pub.dao.PubEnterpriseDao;
import com.ltsznh.modules.pub.entity.PubEnterpriseEntity;
import com.ltsznh.modules.pub.service.PubEnterpriseService;



@Service("pubEnterpriseService")
public class PubEnterpriseServiceImpl implements PubEnterpriseService {
	@Autowired
	private PubEnterpriseDao pubEnterpriseDao;

	@Override
	public PubEnterpriseEntity queryObject(String enterpriseCode){
		return pubEnterpriseDao.queryObject(enterpriseCode);
	}

	@Override
	public List<PubEnterpriseEntity> queryList(Map<String, Object> map){
		return pubEnterpriseDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return pubEnterpriseDao.queryTotal(map);
	}

	@Override
	public void save(PubEnterpriseEntity pubEnterprise){
		pubEnterpriseDao.save(pubEnterprise);
	}

	@Override
	public void update(PubEnterpriseEntity pubEnterprise){
		pubEnterpriseDao.update(pubEnterprise);
	}

	@Override
	public void delete(String enterpriseCode,Long userId){
		pubEnterpriseDao.delete(enterpriseCode,userId);
	}

	@Override
	public void deleteBatch(String[] enterpriseCodes,Long userId){	pubEnterpriseDao.deleteBatch(enterpriseCodes,userId);}

}
