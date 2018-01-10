package com.ltsznh.modules.pub.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 品牌管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-08 15:36:01
 */
public class PubBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//品牌编号
	private Long brandId;
	//品牌
	private String brandName;
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


	private String creatorName;
	private String modifierName;

	/**
	 * 设置：品牌编号
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌编号
	 */
	public Long getBrandId() {
		return brandId;
	}
	/**
	 * 设置：品牌
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：品牌
	 */
	public String getBrandName() {
		return brandName;
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
