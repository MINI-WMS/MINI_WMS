$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferordersal/list',
        datatype: "json",
        colModel: [
            {label: 'toSalId', name: 'toSalId', index: 'to_sal_id', width: 50, key: true, hidden: true},
            {label: '销售日期', name: 'toDate', index: 'to_date', width: 120},
            {label: '销售单号', name: 'toNo', index: 'to_no', width: 150},
            {label: '仓库代码', name: 'warehouseCode', index: 'warehouse_code', width: 120},
            {label: '仓库', name: 'warehouseName', index: 'warehouse_code', width: 150},
            {label: '客户代码', name: 'customerCode', index: 'customer_code', width: 120},
            {label: '客户', name: 'customerName', index: 'customer_code', width: 150},
            {
                label: '状态',
                name: 'dataStatus',
                width: 60,
                index: 'dataStatus',
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">作废</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
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
        showRow: false,
        showSn: false,
        title: null,
        addNew: false,
        wmsTransferOrderSal: {},
        wmsTransferOrderSalRow: {},
        wmsTransferOrderSn: {},
        qRow: {
            toNo: null
        },
        qSn: {
            toNo: null,
            toSeq: null,
        },
        q: {
            toDate: getCurrentDate(),
            toNo: null
        },
        engineers: null,
        salesmans: null,
        defaultCustomerCode: 'lskh'
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.addNew = true;

            vm.wmsTransferOrderSal = {
                toDate: getCurrentDate(),
                warehouseCode: null,
                warehouseName: null,
                customerCode: vm.defaultCustomerCode
            };

            vm.getDefalutWarehouse();
            vm.getWarehouse();


        },
        update: function (event) {
            var toSalId = getSelectedRow();
            if (toSalId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.addNew = false;

            vm.getInfo(toSalId)

            vm.getDefalutWarehouse();
            vm.getWarehouse();

        },
        saveOrUpdate: function (event) {
            var url = vm.wmsTransferOrderSal.toSalId == null ? "wmstransferordersal/save" : "wmstransferordersal/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.wmsTransferOrderSal),
                success: function (r) {
                    if (r.code === 0 && vm.addNew) {
                        var toNo = r.toNo;
                        alert('操作成功，请维护销售出库单明细！' + '\n销售出库单号：' + toNo, function (index) {
                            vm.showRow = true;
                            vm.getInfoForRow(r.toId);
                        });
                    } else if (!vm.addNew) {
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
            var toSalIds = getSelectedRows();
            if (toSalIds == null) {
                return;
            }
            confirm('确定要作废选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "wmstransferordersal/delete",
                    contentType: "application/json",
                    data: JSON.stringify(toSalIds),
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
        getInfo: function (toSalId) {
            $.get(baseURL + "wmstransferordersal/info/" + toSalId, function (r) {
                vm.wmsTransferOrderSal = r.wmsTransferOrderSal;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'toDate': vm.q.toDate, 'toNo': vm.q.toNo},
                page: page
            }).trigger("reloadGrid");
        },
        getInfoForRow: function (toSalId) {
            $.get(baseURL + "wmstransferordersal/info/" + toSalId, function (r) {
                vm.wmsTransferOrderSal = r.wmsTransferOrderSal;
                vm.resetRow();
                vm.reloadRow();
            });
        },
        showRowList: function () {
            var toSalId = getSelectedRow();
            if (toSalId == null) {
                return;
            }
            vm.showRow = true;
            vm.getInfoForRow(toSalId);
        },
        backToSal: function () {
            vm.showRow = false;

            vm.wmsTransferOrderSal = {};
            vm.reload();

            vm.qRow.toNo = "aaa";
            vm.reloadRow();
        },
        resetRow: function () {
            vm.wmsTransferOrderSalRow = {};
            vm.wmsTransferOrderSalRow.toDate = vm.wmsTransferOrderSal.toDate;
            vm.wmsTransferOrderSalRow.toNo = vm.wmsTransferOrderSal.toNo;
            vm.wmsTransferOrderSalRow.warehouseName = vm.wmsTransferOrderSal.warehouseName;
            vm.wmsTransferOrderSalRow.customerName = vm.wmsTransferOrderSal.customerName;
            vm.qRow.toNo = vm.wmsTransferOrderSal.toNo;

        },

//以下是行项信息
        reloadRow: function (event) {
            vm.showList = true;
            var page = $("#jqGridRow").jqGrid('getGridParam', 'page');
            $("#jqGridRow").jqGrid('setGridParam', {
                page: page,
                postData: {
                    'toNo': vm.qRow.toNo
                },
            }).trigger("reloadGrid");
        },
        addRow: function () {
            vm.addNew = true;
            vm.resetRow();
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "新增销售单明细",
                area: ['700px', '400px'],
                shadeClose: false,
                content: jQuery("#addRowLayer")
            });
            $("#materialCodeRow").focus();

            vm.getEngineer();
            vm.getSalesman();

            getPubMaterialSelect2($("#materialCodeRow"));
        },
        saveOrUpdateRow: function (event) {
            var url = vm.addNew ? "wmstransferordersalrow/save" : "wmstransferordersalrow/update";

            //先取商品代码
            if ($('#materialCodeRow').select2('data').length <= 0) {
                msg("请选择商品！");
                return;
            } else {
                vm.wmsTransferOrderSalRow.materialCode = $('#materialCodeRow').select2('data')[0].id;
            }

            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.wmsTransferOrderSalRow),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reloadRow();
                            vm.resetRow();
                            $("#materialCodeRow").focus();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        delRow: function (event) {
            var grid = $("#jqGridRow");
            var rowKey = grid.getGridParam("selrow");
            if (!rowKey) {
                parent.layer.msg("请至少选择一条记录", {offset: 't', anim: 6});
                return;
            }
            var toSalRowIds = grid.getGridParam("selarrrow");

            if (toSalRowIds == null) {
                return;
            }

            confirm('确定要作废选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "wmstransferordersalrow/delete",
                    contentType: "application/json",
                    data: JSON.stringify(toSalRowIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGridRow").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getDefalutWarehouse: function () {
            //获取默认仓库
            $.get(baseURL + "wmswarehouseuser/list", function (r) {
                if (r.code === 0) {
                    // if (r.page.list.size() > 0) {
                    vm.wmsTransferOrderSal.warehouseCode = r.page.list[0].warehouseCode;
                    vm.wmsTransferOrderSal.warehouseName = r.page.list[0].warehouseName;
                    // }
                } else {
                    alert(r.msg);
                }
            })
        },
        getWarehouse: function () {
            //加载仓库树
            $.get(baseURL + "wmswarehouseuser/myWarehouse", function (r) {
                wh_ztree = $.fn.zTree.init($("#whTree"), wh_setting, r.page.list);
                var node = wh_ztree.getNodeByParam("warehouseCode", vm.wmsTransferOrderSal.warehouseCode);
                if (node != null) {
                    wh_ztree.selectNode(node);

                    vm.wmsTransferOrderSal.warehouseName = node.warehouseName;
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
                        vm.wmsTransferOrderSal.warehouseCode = null;
                        vm.wmsTransferOrderSal.warehouseName = null;

                        layer.close(index);
                        return;
                    }
                    //选择上级部门
                    vm.wmsTransferOrderSal.warehouseCode = node[0].warehouseCode;
                    vm.wmsTransferOrderSal.warehouseName = node[0].warehouseName;

                    layer.close(index);
                }
            });
        },
        getInfoRowForSn: function (toRowId) {
            $.get(baseURL + "wmstransferordersalrow/info/" + toRowId, function (r) {
                vm.wmsTransferOrderSalRow = r.wmsTransferOrderSalRow;
                vm.resetSn();
                vm.reloadSn();
            });
        },

        //串号相关操作
        backToRow: function () {
            vm.showSn = false;

            vm.wmsTransferOrderSalRow = {};
            vm.reloadRow();

            vm.qSn.toNo = "aaa";
            vm.reloadSn();
        },
        showSnList: function () {
            var grid = $("#jqGridRow");
            var rowKey = grid.getGridParam("selrow");
            if (!rowKey) {
                parent.layer.msg("请选择一条记录");
                return;
            }

            var selectedIDs = grid.getGridParam("selarrrow");
            if (selectedIDs.length > 1) {
                parent.layer.msg("只能选择一条记录");
                return;
            }

            var toRowId = selectedIDs[0];

            if (toRowId == null) {
                return;
            }
            vm.getInfoRowForSn(toRowId);

            // //打开串号管理界面
            vm.showSn = true;
            // var index = layer.open({
            // 	type: 2,
            // 	skin: 'layui-layer-molv',
            // 	title: "串号管理",
            // 	area: ['700px', '500px'],
            // 	shadeClose: false,
            // 	content: jQuery("#addSnLayer")
            // });
            //
            // layer.full(index);
            $("#materialSn").focus();
        },
        resetSn: function () {
            vm.wmsTransferOrderSn = {};
            vm.wmsTransferOrderSn.toDate = vm.wmsTransferOrderSalRow.toDate;
            vm.wmsTransferOrderSn.toNo = vm.wmsTransferOrderSalRow.toNo;
            vm.wmsTransferOrderSn.toSeq = vm.wmsTransferOrderSalRow.toSeq;
            vm.wmsTransferOrderSn.materialCode = vm.wmsTransferOrderSalRow.materialCode;

            vm.qSn.toNo = vm.wmsTransferOrderSn.toNo;
            vm.qSn.toSeq = vm.wmsTransferOrderSn.toSeq;
        },
        saveOrUpdateSn: function (event) {
            vm.wmsTransferOrderSn.toDate = vm.wmsTransferOrderSalRow.toDate;
            vm.wmsTransferOrderSn.toNo = vm.wmsTransferOrderSalRow.toNo;
            vm.wmsTransferOrderSn.toSeq = vm.wmsTransferOrderSalRow.toSeq;
            vm.wmsTransferOrderSn.materialCode = vm.wmsTransferOrderSalRow.materialCode;

            var url = vm.wmsTransferOrderSn.toSnId == null ? "wmstransferordersn/save" : "wmstransferordersn/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.wmsTransferOrderSn),
                success: function (r) {
                    if (r.code === 0) {
                        // alert('操作成功', function (index) {
                        vm.reloadSn();
                        vm.resetSn();
                        $("#materialSn").focus();
                        // });
                        msg('操作成功');
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        delSn: function (event) {
            var grid = $("#jqGridSn");
            var rowKey = grid.getGridParam("selrow");
            if (!rowKey) {
                parent.layer.msg("请至少选择一条记录", {offset: 't', anim: 6});
                return;
            }
            var toSnIds = grid.getGridParam("selarrrow");

            if (toSnIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "wmstransferordersn/delete",
                    contentType: "application/json",
                    data: JSON.stringify(toSnIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGridSn").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        reloadSn: function (event) {
            vm.showList = true;
            var page = $("#jqGridSn").jqGrid('getGridParam', 'page');
            $("#jqGridSn").jqGrid('setGridParam', {
                page: page,
                postData: {
                    'toNo': vm.qSn.toNo,
                    'toSeq': vm.qSn.toSeq
                },
            }).trigger("reloadGrid");
        }, getEngineer: function () {
            //加载工程师
            $.get(baseURL + "sys/user/getStaff?userType=3", function (r) {//3工程师；4营业员
                // engineer_ztree = $.fn.zTree.init($("#engineerTree"), engineer_setting, r.page.list);
                // var node = engineer_ztree.getNodeByParam("userId", vm.wmsTransferOrderPurRow.engineer);
                // if (node != null) {
                // 	engineer_ztree.selectNode(node);
                //
                // 	vm.wmsTransferOrderPurRow.engineer = node.userId;
                // 	vm.wmsTransferOrderPurRow.engineerName = node.username;
                // }
                if (r.code === 0) {
                    vm.engineers = r.page.list;
                } else {
                    alert(r.msg);
                }
            })
        }, getSalesman: function () {
            //加载营业员
            $.get(baseURL + "sys/user/getStaff?userType=4", function (r) {//3工程师；4营业员
                if (r.code === 0) {
                    vm.salesmans = r.page.list;
                } else {
                    alert(r.msg);
                }
            })
        }
    }
});

$(function () {
    $("#jqGridRow").jqGrid({
        url: baseURL + 'wmstransferordersalrow/list',
        datatype: "json",
        colModel: [
            {label: 'toSalRowId', name: 'toSalRowId', index: 'to_sal_row_id', width: 50, key: true, hidden: true},
            {label: '销售日期', name: 'toDate', index: 'to_date', width: 120},
            {label: '销售单号', name: 'toNo', index: 'to_no', width: 150},
            {label: '序号', name: 'toSeq', index: 'to_seq', width: 80},
            {label: '商品代码', name: 'materialCode', index: 'material_code', width: 120},
            {label: '商品', name: 'materialDesc', index: 'material_code', width: 150},
            // {label: '指导单价', name: 'guidanceUnitPrice', index: 'guidance_unit_price', width: 80},
            {label: '单价', name: 'unitPrice', index: 'unit_price', width: 80},
            {label: '数量', name: 'qty', index: 'qty', width: 80},
            {label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80},
            // {label: '营业员编号', name: 'salesman', index: 'salesman', width: 90},
            {label: '营业员', name: 'salesmanName', index: 'salesman', width: 90},
            // {label: '工程师编号', name: 'engineer', index: 'engineer', width: 90},
            {label: '工程师', name: 'engineerName', index: 'engineer', width: 90},
            {label: '备注', name: 'remark', index: 'remark', width: 90},
            {
                label: '状态',
                name: 'dataStatus',
                width: 60,
                index: 'dataStatus',
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">作废</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
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
        pager: "#jqGridPagerRow",
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
        }
    });
});


/* resize jqGrid*/
$(function () {
    //jqGrid自适应高度
    $(window).on('resize', function () {
        var obj = $("#jqGridRow")
        if (obj.length > 0) {//存在表格
            var height = $(window).height() - obj.offset().top - $("#jqGridPagerRow").outerHeight() - 1;
            obj.setGridHeight(height > 60 ? height : 60);
            // msg("resize!" + obj.toString() + '\n' + $(window).height()+ '\n' + obj.offset().top+ '\n' +  $("#jqGridPager").outerHeight())
        }

    });
})

$(function () {
    $("#jqGridSn").jqGrid({
        url: baseURL + 'wmstransferordersn/list',
        datatype: "json",
        colModel: [
            {label: 'toSnId', name: 'toSnId', index: 'to_sn_id', width: 50, key: true, hidden: true},
            {label: '日期', name: 'toDate', index: 'to_date', width: 120},
            {label: '单据号', name: 'toNo', index: 'to_no', width: 150},
            {label: '序号', name: 'toSeq', index: 'to_seq', width: 80},
            {label: '商品', name: 'materialDesc', index: 'material_code', width: 150},
            {label: '串号', name: 'materialSn', index: 'material_sn', width: 200},
            {
                label: '状态',
                name: 'dataStatus',
                width: 60,
                index: 'dataStatus',
                formatter: function (value, options, row) {
                    return value === 0 ?
                        '<span class="label label-danger">作废</span>' :
                        '<span class="label label-success">正常</span>';
                }
            },
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
        pager: "#jqGridPagerSn",
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
        }
    });
});

/* resize jqGrid*/
$(function () {
    //jqGrid自适应高度
    $(window).on('resize', function () {
        var obj = $("#jqGridSn")
        if (obj.length > 0) {//存在表格
            var height = $(window).height() - obj.offset().top - $("#jqGridPagerSn").outerHeight() - 1;
            obj.setGridHeight(height > 60 ? height : 60);
            // msg("resize!" + obj.toString() + '\n' + $(window).height()+ '\n' + obj.offset().top+ '\n' +  $("#jqGridPager").outerHeight())
        }

    });
})

/* 当前日期 */
function getCurrentDate() {
    var date = new Date();
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var currentDate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
    // console.log(currentDate);
    return currentDate;
}

