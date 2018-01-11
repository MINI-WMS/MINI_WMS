package com.ltsznh.modules.wms.entity;

import com.ltsznh.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * 仓库入出库单
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:03
 */
public class WmsTransferOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//转储单编号
	private Long toId;
	//转储单日期
	private Date toDate;
	//转储单号
	private String toNo;
	//仓库代码
	private String warehouseCode;
	//来源仓库
	private String sourWarehouseCode;
	//目标仓库
	private String destWarehouseCode;
	//状态
	private Integer dataStatus;
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

	private String warehouseName;
	private String sourWarehouseName;
	private String destWarehouseName;

	private String remark;

	/**
	 * 设置：转储单编号
	 */
	public void setToId(Long toId) {
		this.toId = toId;
	}
	/**
	 * 获取：转储单编号
	 */
	public Long getToId() {
		return toId;
	}
	/**
	 * 设置：转储单日期
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	/**
	 * 获取：转储单日期
	 */
	public String getToDate() {
		return DateUtils.formatToDate(toDate);
	}
	/**
	 * 设置：转储单号
	 */
	public void setToNo(String toNo) {
		this.toNo = toNo;
	}
	/**
	 * 获取：转储单号
	 */
	public String getToNo() {
		return toNo;
	}
	/**
	 * 设置：仓库代码
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	/**
	 * 获取：仓库代码
	 */
	public String getWarehouseCode() {
		return warehouseCode;
	}
	/**
	 * 设置：来源仓库
	 */
	public void setSourWarehouseCode(String sourWarehouseCode) {
		this.sourWarehouseCode = sourWarehouseCode;
	}
	/**
	 * 获取：来源仓库
	 */
	public String getSourWarehouseCode() {
		return sourWarehouseCode;
	}
	/**
	 * 设置：目标仓库
	 */
	public void setDestWarehouseCode(String destWarehouseCode) {
		this.destWarehouseCode = destWarehouseCode;
	}
	/**
	 * 获取：目标仓库
	 */
	public String getDestWarehouseCode() {
		return destWarehouseCode;
	}
	/**
	 * 设置：状态
	 */
	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}
	/**
	 * 获取：状态
	 */
	public Integer getDataStatus() {
		return dataStatus;
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


	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getSourWarehouseName() {
		return sourWarehouseName;
	}

	public void setSourWarehouseName(String sourWarehouseName) {
		this.sourWarehouseName = sourWarehouseName;
	}

	public String getDestWarehouseName() {
		return destWarehouseName;
	}

	public void setDestWarehouseName(String destWarehouseName) {
		this.destWarehouseName = destWarehouseName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
