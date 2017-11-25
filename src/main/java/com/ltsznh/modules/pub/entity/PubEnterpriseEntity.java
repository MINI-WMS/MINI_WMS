package com.ltsznh.modules.pub.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 企业
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-11-24 16:46:43
 */
public class PubEnterpriseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//企业代码
	private String enterpriseCode;
	//企业描述
	private String enterpriseDesc;
	//注册地址
	private String registeredAddr;
	//数量小数位
	private Integer qtyDecimal;
	//金额小数位
	private Integer amountDecimal;
	//单价小数位
	private Integer priceDecimal;
	//税务登记证号
	private String taxRegistrationCertificate;
	//注册银行
	private String registeredBank;
	//注册银行
	private String bankAccount;
	//排序
	private Integer orderNum;
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
	 * 设置：企业代码
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	/**
	 * 获取：企业代码
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	/**
	 * 设置：企业描述
	 */
	public void setEnterpriseDesc(String enterpriseDesc) {
		this.enterpriseDesc = enterpriseDesc;
	}
	/**
	 * 获取：企业描述
	 */
	public String getEnterpriseDesc() {
		return enterpriseDesc;
	}
	/**
	 * 设置：注册地址
	 */
	public void setRegisteredAddr(String registeredAddr) {
		this.registeredAddr = registeredAddr;
	}
	/**
	 * 获取：注册地址
	 */
	public String getRegisteredAddr() {
		return registeredAddr;
	}
	/**
	 * 设置：数量小数位
	 */
	public void setQtyDecimal(Integer qtyDecimal) {
		this.qtyDecimal = qtyDecimal;
	}
	/**
	 * 获取：数量小数位
	 */
	public Integer getQtyDecimal() {
		return qtyDecimal;
	}
	/**
	 * 设置：金额小数位
	 */
	public void setAmountDecimal(Integer amountDecimal) {
		this.amountDecimal = amountDecimal;
	}
	/**
	 * 获取：金额小数位
	 */
	public Integer getAmountDecimal() {
		return amountDecimal;
	}
	/**
	 * 设置：单价小数位
	 */
	public void setPriceDecimal(Integer priceDecimal) {
		this.priceDecimal = priceDecimal;
	}
	/**
	 * 获取：单价小数位
	 */
	public Integer getPriceDecimal() {
		return priceDecimal;
	}
	/**
	 * 设置：税务登记证号
	 */
	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}
	/**
	 * 获取：税务登记证号
	 */
	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}
	/**
	 * 设置：注册银行
	 */
	public void setRegisteredBank(String registeredBank) {
		this.registeredBank = registeredBank;
	}
	/**
	 * 获取：注册银行
	 */
	public String getRegisteredBank() {
		return registeredBank;
	}
	/**
	 * 设置：注册银行
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 获取：注册银行
	 */
	public String getBankAccount() {
		return bankAccount;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
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
