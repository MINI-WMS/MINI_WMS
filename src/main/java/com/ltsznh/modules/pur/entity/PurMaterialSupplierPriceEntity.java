package com.ltsznh.modules.pur.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 物料供应商价格
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-15 12:01:24
 */
public class PurMaterialSupplierPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//colName
	private Long mspId;
	//物料代码
	private String materialCode;
	//供应商代码
	private String supplierCode;
	//采购单价
	private BigDecimal purchaseUnitPrice;
	//合同开始日期
	private Date contractStartDate;
	//合同截止日期
	private Date contractEndDate;
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

	/**
	 * 设置：colName
	 */
	public void setMspId(Long mspId) {
		this.mspId = mspId;
	}
	/**
	 * 获取：colName
	 */
	public Long getMspId() {
		return mspId;
	}
	/**
	 * 设置：物料代码
	 */
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	/**
	 * 获取：物料代码
	 */
	public String getMaterialCode() {
		return materialCode;
	}
	/**
	 * 设置：供应商代码
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	 * 获取：供应商代码
	 */
	public String getSupplierCode() {
		return supplierCode;
	}
	/**
	 * 设置：采购单价
	 */
	public void setPurchaseUnitPrice(BigDecimal purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}
	/**
	 * 获取：采购单价
	 */
	public BigDecimal getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}
	/**
	 * 设置：合同开始日期
	 */
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	/**
	 * 获取：合同开始日期
	 */
	public Date getContractStartDate() {
		return contractStartDate;
	}
	/**
	 * 设置：合同截止日期
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * 获取：合同截止日期
	 */
	public Date getContractEndDate() {
		return contractEndDate;
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
}
