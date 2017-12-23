package com.ltsznh.modules.sal.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 销售出库
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 10:46:27
 */
public class WmsTransferOrderSalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//转储单编号
	private Long toSalId;
	//转储单日期
	private Date toDate;
	//转储单号
	private String toNo;
	//客户代码
	private String customerCode;
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
	public void setToSalId(Long toSalId) {
		this.toSalId = toSalId;
	}
	/**
	 * 获取：转储单编号
	 */
	public Long getToSalId() {
		return toSalId;
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
