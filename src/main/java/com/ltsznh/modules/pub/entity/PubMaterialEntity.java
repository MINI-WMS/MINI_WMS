package com.ltsznh.modules.pub.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * 物料
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public class PubMaterialEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//物料代码
	private String materialCode;
	//物料描述
	private String materialDesc;
	//物料类型
	private String materialTypeCode;
	//库存单位
	private String inventoryUnit;
	//采购单位
	private String purchaseUnit;
	//销售单位
	private String saleUnit;
	//采购库存单位转换比
	private BigDecimal purchaseToInventory;
	//销售库存单位转换比
	private BigDecimal saleToInventory;
	//税率
	private BigDecimal taxRate;
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

	private String materialTypeDesc;

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
	 * 设置：物料描述
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	/**
	 * 获取：物料描述
	 */
	public String getMaterialDesc() {
		return materialDesc;
	}
	/**
	 * 设置：物料类型
	 */
	public void setMaterialTypeCode(String materialTypeCode) {
		this.materialTypeCode = materialTypeCode;
	}
	/**
	 * 获取：物料类型
	 */
	public String getMaterialTypeCode() {
		return materialTypeCode;
	}
	/**
	 * 设置：库存单位
	 */
	public void setInventoryUnit(String inventoryUnit) {
		this.inventoryUnit = inventoryUnit;
	}
	/**
	 * 获取：库存单位
	 */
	public String getInventoryUnit() {
		return inventoryUnit;
	}
	/**
	 * 设置：采购单位
	 */
	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}
	/**
	 * 获取：采购单位
	 */
	public String getPurchaseUnit() {
		return purchaseUnit;
	}
	/**
	 * 设置：销售单位
	 */
	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}
	/**
	 * 获取：销售单位
	 */
	public String getSaleUnit() {
		return saleUnit;
	}
	/**
	 * 设置：采购库存单位转换比
	 */
	public void setPurchaseToInventory(BigDecimal purchaseToInventory) {
		this.purchaseToInventory = purchaseToInventory;
	}
	/**
	 * 获取：采购库存单位转换比
	 */
	public BigDecimal getPurchaseToInventory() {
		return purchaseToInventory;
	}
	/**
	 * 设置：销售库存单位转换比
	 */
	public void setSaleToInventory(BigDecimal saleToInventory) {
		this.saleToInventory = saleToInventory;
	}
	/**
	 * 获取：销售库存单位转换比
	 */
	public BigDecimal getSaleToInventory() {
		return saleToInventory;
	}
	/**
	 * 设置：税率
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * 获取：税率
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
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

	public String getMaterialTypeDesc() {
		return materialTypeDesc;
	}

	public void setMaterialTypeDesc(String materialTypeDesc) {
		this.materialTypeDesc = materialTypeDesc;
	}
}
