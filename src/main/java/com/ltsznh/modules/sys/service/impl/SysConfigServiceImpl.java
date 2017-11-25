package com.ltsznh.modules.sys.service.impl;

import com.google.gson.Gson;

import com.ltsznh.modules.sys.dao.SysConfigDao;
import com.ltsznh.modules.sys.entity.SysConfigEntity;
import com.ltsznh.common.exception.FamilyException;
import com.ltsznh.modules.sys.redis.SysConfigRedis;
import com.ltsznh.modules.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private SysConfigRedis sysConfigRedis;
	
	@Override
	@Transactional
	public void save(SysConfigEntity config) {
		sysConfigDao.save(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional
	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional
	public void updateValueByKey(String configKey, String configValue) {
		sysConfigDao.updateValueByKey(configKey, configValue);
		sysConfigRedis.delete(configKey);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] ids,Long userId) {
		for(Long id : ids){
			SysConfigEntity config = queryObject(id);
			sysConfigRedis.delete(config.getConfigKey());
		}

		sysConfigDao.deleteBatch(ids,userId);
	}

	@Override
	public List<SysConfigEntity> queryList(Map<String, Object> map) {
		return sysConfigDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysConfigDao.queryTotal(map);
	}

	@Override
	public SysConfigEntity queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}

	@Override
	public String getValue(String configKey) {
		SysConfigEntity config = sysConfigRedis.get(configKey);
		if(config == null){
			config = sysConfigDao.queryByKey(configKey);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getConfigValue();
	}
	
	@Override
	public <T> T getConfigObject(String configKey, Class<T> clazz) {
		String value = getValue(configKey);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new FamilyException("获取参数失败");
		}
	}
}
