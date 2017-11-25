package com.ltsznh.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 部门管理
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-06-20 15:23:47
 */
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//部门ID
	private Long deptId;
	//上级部门ID，一级部门为0
	private Long parentDeptId;
	//部门名称
	private String deptName;
	//上级部门名称
	private String parentDeptName;
	//排序
	private Integer orderNum;

	private int deptType;
	private String deptAddr;

	private int isEnabled;

	private Date createDate;
	private Long creatorId;
	private String creatorName;

	private Date modifyDate;
	private Long modifierId;
	private String modifierName;

	/**
	 * ztree属性
	 */
	private Boolean open;

	private List<?> list;


	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return deptId;
	}

	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentDeptId(Long parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public Long getParentDeptId() {
		return parentDeptId;
	}

	/**
	 * 设置：部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 获取：部门名称
	 */
	public String getDeptName() {
		return deptName;
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

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}

	public String getDeptAddr() {
		return deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
}
