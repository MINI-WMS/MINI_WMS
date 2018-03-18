package com.ltsznh.modules.wms.entity;

import com.ltsznh.common.utils.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 仓库库存
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:55
 */
public class WmsWarehouseInventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//仓库结存编号
	private Long wiId;
	//结存日期
	private Date inventoryDate;
	//仓库编号
	private String warehouseCode;
	//物料代码
	private String materialCode;
	//数量
	private Long qty;
	//总金额
	private BigDecimal totalAmount;
    //工程师
    private Long engineer;
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

    //物料代码
    private String materialDesc;
    private String warehouseName;
    private String engineerName;
	/**
	 * 设置：仓库结存编号
	 */
	public void setWiId(Long wiId) {
		this.wiId = wiId;
	}
	/**
	 * 获取：仓库结存编号
	 */
	public Long getWiId() {
		return wiId;
	}
	/**
	 * 设置：结存日期
	 */
	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	/**
	 * 获取：结存日期
	 */
	public String getInventoryDate() {
        return DateUtils.formatToDate(inventoryDate);
	}
	/**
	 * 设置：仓库编号
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	/**
	 * 获取：仓库编号
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

    public Long getEngineer() {
        return engineer;
    }

    public void setEngineer(Long engineer) {
        this.engineer = engineer;
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

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }
}
