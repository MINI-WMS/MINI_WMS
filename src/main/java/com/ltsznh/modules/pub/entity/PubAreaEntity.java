package com.ltsznh.modules.pub.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 地区
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public class PubAreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//地区代码
	private String areaCode;
	//地区名称
	private String areaName;
	//上级地区代码
	private String parentAreaCode;
	//地区类型
	private Integer areaType;
	//状态
	private Integer isEnabled;
	//创建用户
	private Long creatorId;
	//创建时间
	private Date createDate;
	//修改用户
	private Long modifierId;
	//修改时间
	private Date modifyDate;

	private String parentAreaName;

	private String creatorName;
	private String modifierName;

	/**
	 * 设置：地区代码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：地区代码
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地区名称
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：上级地区代码
	 */
	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}
	/**
	 * 获取：上级地区代码
	 */
	public String getParentAreaCode() {
		return parentAreaCode;
	}
	/**
	 * 设置：地区类型
	 */
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	/**
	 * 获取：地区类型
	 */
	public Integer getAreaType() {
		return areaType;
	}
	/**
	 * 设置：状态
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 获取：状态
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}
	/**
	 * 设置：创建用户
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：创建用户
	 */
	public Long getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：修改用户
	 */
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	/**
	 * 获取：修改用户
	 */
	public Long getModifierId() {
		return modifierId;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
}
