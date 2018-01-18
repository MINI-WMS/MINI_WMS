package com.ltsznh.modules.wms.dao;

import com.ltsznh.modules.sys.dao.BaseDao;
import com.ltsznh.modules.wms.entity.PubSnEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 单据日期
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-12 11:28:32
 */
public interface PubSnDao extends BaseDao<PubSnEntity> {


	int DayEndClearing(@Param("array")Object[] id, @Param("modifierId")Long modifierId);

	int DayEndClearing2(@Param("array")Object[] id,@Param("modifierId")Long modifierId);
}
