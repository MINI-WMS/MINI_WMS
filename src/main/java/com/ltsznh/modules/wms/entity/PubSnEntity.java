package com.ltsznh.modules.wms.entity;

import com.ltsznh.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 单据日期
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2018-01-12 11:28:32
 */
public class PubSnEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//编号
	private Long snId;
	//单据日期
	private Date snDate;
	//单据类型
	private String snType;
	//当前编号
	private Integer snNum;
	//状态
	private Integer snStatus;
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
	 * 设置：编号
	 */
	public void setSnId(Long snId) {
		this.snId = snId;
	}

	/**
	 * 获取：编号
	 */
	public Long getSnId() {
		return snId;
	}

	/**
	 * 设置：单据日期
	 */
	public void setSnDate(Date snDate) {
		this.snDate = snDate;
	}

	/**
	 * 获取：单据日期
	 */
	public String getSnDate() {
		return DateUtils.formatToDate(snDate);
	}

	/**
	 * 设置：单据类型
	 */
	public void setSnType(String snType) {
		this.snType = snType;
	}

	/**
	 * 获取：单据类型
	 */
	public String getSnType() {
		return snType;
	}

	/**
	 * 设置：当前编号
	 */
	public void setSnNum(Integer snNum) {
		this.snNum = snNum;
	}

	/**
	 * 获取：当前编号
	 */
	public Integer getSnNum() {
		return snNum;
	}

	/**
	 * 设置：状态
	 */
	public void setSnStatus(Integer snStatus) {
		this.snStatus = snStatus;
	}

	/**
	 * 获取：状态
	 */
	public Integer getSnStatus() {
		return snStatus;
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
