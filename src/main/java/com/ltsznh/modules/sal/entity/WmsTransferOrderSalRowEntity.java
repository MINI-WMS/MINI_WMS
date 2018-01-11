package com.ltsznh.modules.sal.entity;

import com.ltsznh.common.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 销售出库明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
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
	//物料代码
	private String materialCode;
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


	private String creatorName;
	private String modifierName;

	private String materialDesc;
	private String engineerName;

	private Long salesman;
	private String salesmanName;

	private String remark;

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

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public Long getSalesman() {
		return salesman;
	}

	public void setSalesman(Long salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
