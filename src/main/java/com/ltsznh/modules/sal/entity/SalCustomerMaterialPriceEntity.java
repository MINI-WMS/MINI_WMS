package com.ltsznh.modules.sal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 客户物料价格
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:26
 */
public class SalCustomerMaterialPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long mcpId;
	//物料代码
	private String materialCode;
	//客户代码
	private String customerCode;
	//销售单价
	private BigDecimal saleUnitPrice;
	//开始日期
	private Date contractStartDate;
	//截止日期
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
	 * 设置：编号
	 */
	public void setMcpId(Long mcpId) {
		this.mcpId = mcpId;
	}
	/**
	 * 获取：编号
	 */
	public Long getMcpId() {
		return mcpId;
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
	 * 设置：销售单价
	 */
	public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
		this.saleUnitPrice = saleUnitPrice;
	}
	/**
	 * 获取：销售单价
	 */
	public BigDecimal getSaleUnitPrice() {
		return saleUnitPrice;
	}
	/**
	 * 设置：开始日期
	 */
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	/**
	 * 获取：开始日期
	 */
	public Date getContractStartDate() {
		return contractStartDate;
	}
	/**
	 * 设置：截止日期
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * 获取：截止日期
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
