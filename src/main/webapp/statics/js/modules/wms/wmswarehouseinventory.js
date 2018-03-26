$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmswarehouseinventory/list',
        datatype: "json",
        colModel: [
            {label: 'wiId', name: 'wiId', index: 'wi_id', width: 50, key: true, hidden: true},
            {label: '结存日期', name: 'inventoryDate', index: 'inventory_date', width: 120},
            {label: '仓库', name: 'warehouseName', index: 'warehouse_code', width: 120},
            {label: '商品', name: 'materialDesc', index: 'material_code', width: 150},
            {label: '工程师', name: 'engineerName', index: 'engineer', width: 80},
            {label: '数量', name: 'qty', index: 'qty', width: 80},
            {label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80},

            {label: '创建用户', name: 'creatorName', index: 'creator_id', width: 80},
            {label: '创建时间', name: 'createDate', index: 'create_date', width: 150},
            {label: '修改用户', name: 'modifierName', index: 'modifier_id', width: 80},
            {label: '修改时间', name: 'modifyDate', index: 'modify_date', width: 150}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        shrinkToFit: false,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $(window).resize();
            //隐藏grid底部滚动条
            // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

//仓库结构树
var wh_ztree;
var wh_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "warehouseCode",
            pIdKey: "warehouseCode1",
            rootPId: -1
        },
        key: {
            name: "warehouseName",
            url: "nourl"
        }
    }
};

var vm = new Vue({
    el: '#familyApp',
    data: {
        showList: true,
        title: null,
        addNew: false,
        wmsWarehouseInventory: {},
        q: {
            warehouseCode: null,
            warehouseName: null,
            materialCode: null,
            engineer: null,
            inventoryDate: null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.addNew = true;
            vm.wmsWarehouseInventory = {inventoryDate: getCurrentDate(), warehouseCode: null, warehouseName: null,};

            vm.getDefalutWarehouse();
            vm.getWarehouse();

            getPubMaterialSelect2($("#materialCode"));
            getEngineerSelect2($("#engineer"));
        },
        update: function (event) {
            var wiId = getSelectedRow();
            if (wiId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.addNew = false;

            vm.getWarehouse();


            vm.getInfo(wiId);
        },
        saveOrUpdate: function (event) {
            //先取商品代码
            if ($('#materialCode').select2('data').length <= 0) {
                msg("请选择商品！");
                return;
            } else {
                vm.wmsWarehouseInventory.materialCode = $('#materialCode').select2('data')[0].id;
            }

            //工程师
            if ($('#engineer').select2('data').length > 0) {
                vm.wmsWarehouseInventory.engineer = $('#engineer').select2('data')[0].id;
            }

            var url = vm.addNew ? "wmswarehouseinventory/save" : "wmswarehouseinventory/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.wmsWarehouseInventory),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var wiIds = getSelectedRows();
            if (wiIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "wmswarehouseinventory/delete",
                    contentType: "application/json",
                    data: JSON.stringify(wiIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (wiId) {
            getPubMaterialSelect2($("#materialCode"));
            getEngineerSelect2($("#engineer"));

            $.get(baseURL + "wmswarehouseinventory/info/" + wiId, function (r) {
                vm.wmsWarehouseInventory = r.wmsWarehouseInventory;
            });
        },
        reload: function (event) {
            if ($('#materialCodeQ').select2('data').length > 0) {
                vm.q.materialCode = $('#materialCodeQ').select2('data')[0].id;
            }else{
                vm.q.materialCode ="";
            }
            if ($('#engineerQ').select2('data').length > 0) {
                vm.q.engineer = $('#engineerQ').select2('data')[0].id;
            }else {
                vm.q.engineer = "";
            }
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'warehouseCode': vm.q.warehouseCode,
                    'materialCode': vm.q.materialCode,
                    'engineer': vm.q.engineer,
                    'inventoryDate': vm.q.inventoryDate
                },
                page: page
            }).trigger("reloadGrid");
        },
        getWarehouse: function () {
            //加载仓库树
            $.get(baseURL + "wmswarehouseuser/myWarehouse", function (r) {
                wh_ztree = $.fn.zTree.init($("#whTree"), wh_setting, r.page.list);
                var node = wh_ztree.getNodeByParam("warehouseCode", vm.wmsWarehouseInventory.warehouseCode);
                if (node != null) {
                    wh_ztree.selectNode(node);

                    vm.wmsWarehouseInventory.warehouseName = node.warehouseName;
                }
            })
        },
        whTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择仓库",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#whLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = wh_ztree.getSelectedNodes();
                    if (node === null) {
                        vm.wmsWarehouseInventory.warehouseCode = null;
                        vm.wmsWarehouseInventory.warehouseName = null;

                        layer.close(index);
                        return;
                    }
                    //选择
                    vm.wmsWarehouseInventory.warehouseCode = node[0].warehouseCode;
                    vm.wmsWarehouseInventory.warehouseName = node[0].warehouseName;

                    layer.close(index);
                }
            });
        },
        whTreeQ: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择仓库",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#whLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = wh_ztree.getSelectedNodes();
                    if (node === null) {
                        vm.q.warehouseCode = null;
                        vm.q.warehouseName = null;

                        layer.close(index);
                        return;
                    }
                    //选择
                    vm.q.warehouseCode = node[0].warehouseCode;
                    vm.q.warehouseName = node[0].warehouseName;

                    layer.close(index);
                }
            });
        },
        getDefalutWarehouse: function () {
            //获取默认仓库
            $.get(baseURL + "wmswarehouseuser/list", function (r) {
                if (r.code === 0) {
                    // if (r.page.list.size() > 0) {
                    vm.wmsWarehouseInventory.warehouseCode = r.page.list[0].warehouseCode;
                    vm.wmsWarehouseInventory.warehouseName = r.page.list[0].warehouseName;
                    // }
                } else {
                    alert(r.msg);
                }
            })
        },
        downloadTemplate: function () {
            //获取默认仓库
            var url = baseURL + "download/template/excel?fileName=导入-仓库库存.xlsx";
            // $.get(baseURL + "download/template/excel?fileName=导入-仓库库存.xlsx", function (r) {
            // });
            // $.post(baseURL + "download/template/excel?fileName=导入-仓库库存.xlsx", function (r) {
            // })
            window.open(encodeURI(url));
        }
    }
});

vm.getWarehouse();

getPubMaterialSelect2($("#materialCode"));
getEngineerSelect2($("#engineer"));

getPubMaterialSelect2($("#materialCodeQ"));
getEngineerSelect2($("#engineerQ"));


var ajaxupload = new AjaxUpload($("#uploadFile"), {
    action: baseURL + "upload/excel",
    type: "POST",
    data: {},
    autoSubmit: true,
    responseType: "json",
    name: 'file',
    onChange: function (file, ext) {//在选中了文件的时候触发
        if (!(ext && /^(xls|xlsx)$/.test(ext))) {
            msg("只支持xls格式的文件");
        }
    },
    onSubmit: function (file, extension) {//在提交的时候触发
        msg("正在上传文件，请稍候！");
    },
    onComplete: function (file, resp) {//上传结束的时候触发
        if (resp.code === 0) {//excel上传成功，开始excel导入的方法
            $.ajax({
                type: "POST",
                url: baseURL + "wmswarehouseinventory/importExcel",
                contentType: "application/json",
                data: JSON.stringify(resp.msg),
                success: function (r) {
                    if (r.code === 0) {
                        alert(r.msg, function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        } else {
            msg("上传失败:" + resp.msg);
        }

    }
});