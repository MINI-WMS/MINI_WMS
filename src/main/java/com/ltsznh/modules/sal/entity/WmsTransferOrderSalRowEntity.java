package com.ltsznh.modules.sal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 销售出库明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
public class WmsTransferOrderSalRowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//转储单编号
	private Long toSalRowId;
	//转储单日期
	private Date toDate;
	//转储单号
	private String toNo;
	//转储单序号
	private Integer toSeq;
	//仓库代码
	private String warehouseCode;
	//物料代码
	private String materialCode;
	//客户代码
	private String customerCode;
	//指导单价
	private BigDecimal guidanceUnitPrice;
	//单价
	private BigDecimal unitPrice;
	//数量
	private Long qty;
	//总金额
	private BigDecimal totalAmount;
	//移动类型
	private String moveTypeCode;
	//状态
	private Integer dataStatus;
	//所属工程师
	private Long engineer;
	//创建用户
	private Long creatorId;
	//创建时间
	private Date createDate;
	//修改用户
	private Long modifierId;
	//修改时间
	private Date modifyDate;

	/**
	 * 设置：转储单编号
	 */
	public void setToSalRowId(Long toSalRowId) {
		this.toSalRowId = toSalRowId;
	}
	/**
	 * 获取：转储单编号
	 */
	public Long getToSalRowId() {
		return toSalRowId;
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
	public Date getToDate() {
		return toDate;
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
	 * 设置：转储单序号
	 */
	public void setToSeq(Integer toSeq) {
		this.toSeq = toSeq;
	}
	/**
	 * 获取：转储单序号
	 */
	public Integer getToSeq() {
		return toSeq;
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
	 * 设置：客户代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	/**
	 * 获取：客户代码
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	/**
	 * 设置：指导单价
	 */
	public void setGuidanceUnitPrice(BigDecimal guidanceUnitPrice) {
		this.guidanceUnitPrice = guidanceUnitPrice;
	}
	/**
	 * 获取：指导单价
	 */
	public BigDecimal getGuidanceUnitPrice() {
		return guidanceUnitPrice;
	}
	/**
	 * 设置：单价
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 设置：数量
	 */
	public void setQty(Long qty) {
		this.qty = qty;
	}
	/**
	 * 获取：数量
	 */
	public Long getQty() {
		return qty;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：总金额
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：移动类型
	 */
	public void setMoveTypeCode(String moveTypeCode) {
		this.moveTypeCode = moveTypeCode;
	}
	/**
	 * 获取：移动类型
	 */
	public String getMoveTypeCode() {
		return moveTypeCode;
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
	 * 设置：所属工程师
	 */
	public void setEngineer(Long engineer) {
		this.engineer = engineer;
	}
	/**
	 * 获取：所属工程师
	 */
	public Long getEngineer() {
		return engineer;
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