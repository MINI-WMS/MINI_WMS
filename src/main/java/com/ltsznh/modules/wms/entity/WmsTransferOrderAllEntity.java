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
 * @date 2018-03-19 15:19:41
 */
public class WmsTransferOrderAllEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
    //所属工程师
    private Long engineer;
    //状态
    private Integer dataStatus;

    //物料代码
    private String materialDesc;
    private String warehouseName;
    private String engineerName;

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

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }
}
