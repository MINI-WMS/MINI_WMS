package com.ltsznh.modules.wms.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 物料SN明细
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-25 17:43:17
 */
public class WmsTransferOrderSnEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//colName
	private Long toSnId;
	//转储单日期
	private Date toDate;
	//转储单号
	private String toNo;
	//转储单序号
	private Integer toSeq;
	//物料代码
	private String materialCode;
	//物料SN
	private String materialSn;
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

	/**
	 * 设置：colName
	 */
	public void setToSnId(Long toSnId) {
		this.toSnId = toSnId;
	}
	/**
	 * 获取：colName
	 */
	public Long getToSnId() {
		return toSnId;
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
	 * 设置：物料SN
	 */
	public void setMaterialSn(String materialSn) {
		this.materialSn = materialSn;
	}
	/**
	 * 获取：物料SN
	 */
	public String getMaterialSn() {
		return materialSn;
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
}
