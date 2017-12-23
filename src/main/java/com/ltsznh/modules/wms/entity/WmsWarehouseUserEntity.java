package com.ltsznh.modules.wms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 用户仓库权限管理
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:54
 */
public class WmsWarehouseUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//colName
	private Long wmsUserId;
	//colName
	private Long userId;
	//colName
	private String warehouseCode;
	//创建用户
	private Long creatorId;
	//创建时间
	private Date createDate;

	private Long deptId;
	private String deptName;
	private String username;
	private List<String> warehouseCodeList;
	private String warehouseName;

	private String creatorName;

	/**
	 * 设置：colName
	 */
	public void setWmsUserId(Long wmsUserId) {
		this.wmsUserId = wmsUserId;
	}

	/**
	 * 获取：colName
	 */
	public Long getWmsUserId() {
		return wmsUserId;
	}

	/**
	 * 设置：colName
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：colName
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置：colName
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	/**
	 * 获取：colName
	 */
	public String getWarehouseCode() {
		return warehouseCode;
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


	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public List<String> getWarehouseCodeList() {
		return warehouseCodeList;
	}

	public void setWarehouseCodeList(List<String> warehouseCodeList) {
		this.warehouseCodeList = warehouseCodeList;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
}
