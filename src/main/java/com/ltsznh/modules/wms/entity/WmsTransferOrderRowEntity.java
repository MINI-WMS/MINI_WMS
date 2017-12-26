package com.ltsznh.modules.wms.entity;

import com.ltsznh.common.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 仓库入出库单明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 22:17:04
 */
public class WmsTransferOrderRowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//转储单编号
	private Long toRowId;
	//colName
	private Long sourToId;
	//转储单日期
	private Date toDate;
	//转储单号
	private String toNo;
	//转储单序号
	private Integer toSeq;
	//所属工程师
	private Long engineer;
	//来源工程师
	private Long sourEngineer;
	//目标工程师
	private Long destEngineer;
	//物料代码
	private String materialCode;
	//供应商代码
	private String supplierCode;
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
	//确认用户
	private Long confirmId;
	//确认时间
	private Date confirmTime;
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


	private String sourEngineerName;
	private String destEngineerName;
	private String materialDesc;
	private String supplierName;

	/**
	 * 设置：转储单编号
	 */
	public void setToRowId(Long toRowId) {
		this.toRowId = toRowId;
	}
	/**
	 * 获取：转储单编号
	 */
	public Long getToRowId() {
		return toRowId;
	}
	/**
	 * 设置：colName
	 */
	public void setSourToId(Long sourToId) {
		this.sourToId = sourToId;
	}
	/**
	 * 获取：colName
	 */
	public Long getSourToId() {
		return sourToId;
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
	 * 设置：来源工程师
	 */
	public void setSourEngineer(Long sourEngineer) {
		this.sourEngineer = sourEngineer;
	}
	/**
	 * 获取：来源工程师
	 */
	public Long getSourEngineer() {
		return sourEngineer;
	}
	/**
	 * 设置：目标工程师
	 */
	public void setDestEngineer(Long destEngineer) {
		this.destEngineer = destEngineer;
	}
	/**
	 * 获取：目标工程师
	 */
	public Long getDestEngineer() {
		return destEngineer;
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
	 * 设置：确认用户
	 */
	public void setConfirmId(Long confirmId) {
		this.confirmId = confirmId;
	}
	/**
	 * 获取：确认用户
	 */
	public Long getConfirmId() {
		return confirmId;
	}
	/**
	 * 设置：确认时间
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * 获取：确认时间
	 */
	public Date getConfirmTime() {
		return confirmTime;
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

	public String getSourEngineerName() {
		return sourEngineerName;
	}

	public void setSourEngineerName(String sourEngineerName) {
		this.sourEngineerName = sourEngineerName;
	}

	public String getDestEngineerName() {
		return destEngineerName;
	}

	public void setDestEngineerName(String destEngineerName) {
		this.destEngineerName = destEngineerName;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
