package com.ltsznh.modules.wms.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.ltsznh.common.utils.*;
import com.ltsznh.modules.sys.controller.AbstractController;
import com.ltsznh.common.annotation.SysLog;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltsznh.modules.wms.entity.WmsWarehouseInventoryEntity;
import com.ltsznh.modules.wms.service.WmsWarehouseInventoryService;


/**
 * 仓库库存
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-12-22 14:14:55
 */
@RestController
@RequestMapping("wmswarehouseinventory")
public class WmsWarehouseInventoryController extends AbstractController {
    @Autowired
    private WmsWarehouseInventoryService wmsWarehouseInventoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wmswarehouseinventory:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<WmsWarehouseInventoryEntity> wmsWarehouseInventoryList = wmsWarehouseInventoryService.queryList(query);
        int total = wmsWarehouseInventoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(wmsWarehouseInventoryList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{wiId}")
    @RequiresPermissions("wmswarehouseinventory:info")
    public R info(@PathVariable("wiId") Long wiId) {
        WmsWarehouseInventoryEntity wmsWarehouseInventory = wmsWarehouseInventoryService.queryObject(wiId);

        return R.ok().put("wmsWarehouseInventory", wmsWarehouseInventory);
    }

    /**
     * Excel导入
     */
    @SysLog("Excel导入仓库库存")
    @RequestMapping("/importExcel")
    @RequiresPermissions("wmswarehouseinventory:save")
    public R importExcel(@RequestBody String excelName) {
        File importFile = new File(ConfigParam.getUploadExcelPath(), excelName);
        String fileExtension = FilenameUtils.getExtension(excelName);
        Workbook workbook = new ExcelUtils().getWorkbook(importFile);

//        if(fileExtension.equalsIgnoreCase("xls")){
//
//        }
        //获取第一个sheet表
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        int okCount = 0;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            try {
                row = sheet.getRow(i);
                WmsWarehouseInventoryEntity wmsWarehouseInventory = new WmsWarehouseInventoryEntity();
                wmsWarehouseInventory.setInventoryDate(DateUtils.formatToDate(row.getCell(0).getStringCellValue()));
                wmsWarehouseInventory.setWarehouseCode(row.getCell(1).getStringCellValue());
                wmsWarehouseInventory.setEngineer(Long.parseLong(row.getCell(2).getStringCellValue()));
                wmsWarehouseInventory.setMaterialCode(row.getCell(3).getStringCellValue());
                wmsWarehouseInventory.setQty(Long.parseLong(row.getCell(4).getStringCellValue()));
                wmsWarehouseInventory.setTotalAmount(new BigDecimal(row.getCell(5).getStringCellValue()));

                wmsWarehouseInventory.setCreatorId(getUserId());
                wmsWarehouseInventory.setCreateDate(new Date());

                wmsWarehouseInventoryService.save(wmsWarehouseInventory);
                okCount++;
            } catch (Exception e) {
                continue;
            }
        }
        importFile.deleteOnExit();
        importFile.delete();
        if (okCount == sheet.getLastRowNum()) {
            return R.ok("共有" + sheet.getLastRowNum() + "条记录，已经全部导入成功！");
        } else {
            return R.ok("共有" + sheet.getLastRowNum() + "条记录，成功导入：" + okCount + "条记录");
        }
    }

    /**
     * 保存
     */
    @SysLog("保存仓库库存")
    @RequestMapping("/save")
    @RequiresPermissions("wmswarehouseinventory:save")
    public R save(@RequestBody WmsWarehouseInventoryEntity wmsWarehouseInventory) {
        wmsWarehouseInventory.setCreatorId(getUserId());
        wmsWarehouseInventory.setCreateDate(new Date());

        wmsWarehouseInventoryService.save(wmsWarehouseInventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改仓库库存")
    @RequestMapping("/update")
    @RequiresPermissions("wmswarehouseinventory:update")
    public R update(@RequestBody WmsWarehouseInventoryEntity wmsWarehouseInventory) {
        wmsWarehouseInventory.setModifierId(getUserId());
        wmsWarehouseInventory.setModifyDate(new Date());

        wmsWarehouseInventoryService.update(wmsWarehouseInventory);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除仓库库存")
    @RequestMapping("/delete")
    @RequiresPermissions("wmswarehouseinventory:delete")
    public R delete(@RequestBody Long[] wiIds) {
        wmsWarehouseInventoryService.deleteBatch(wiIds, getUserId());

        return R.ok();
    }

}
