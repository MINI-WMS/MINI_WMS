package com.ltsznh.modules.wms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 库存移动类型
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:53
 */
public class WmsMoveTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//移动类型代码
	private String moveTypeCode;
	//移动类型名称
	private String moveTypeDesc;
	//移动方向
	private Integer moveDirection;
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
	 * 设置：移动类型代码
	 */
	public void setMoveTypeCode(String moveTypeCode) {
		this.moveTypeCode = moveTypeCode;
	}
	/**
	 * 获取：移动类型代码
	 */
	public String getMoveTypeCode() {
		return moveTypeCode;
	}
	/**
	 * 设置：移动类型名称
	 */
	public void setMoveTypeDesc(String moveTypeDesc) {
		this.moveTypeDesc = moveTypeDesc;
	}
	/**
	 * 获取：移动类型名称
	 */
	public String getMoveTypeDesc() {
		return moveTypeDesc;
	}
	/**
	 * 设置：移动方向
	 */
	public void setMoveDirection(Integer moveDirection) {
		this.moveDirection = moveDirection;
	}
	/**
	 * 获取：移动方向
	 */
	public Integer getMoveDirection() {
		return moveDirection;
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
