package com.ltsznh.modules.sys.dao;


import com.ltsznh.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年12月4日 下午6:46:16
 */
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("configKey") String key, @Param("configValue") String value);
	
}
