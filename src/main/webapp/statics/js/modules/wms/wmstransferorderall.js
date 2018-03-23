$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferorderall/list',
        datatype: "json",
        colModel: [
            {label: '结存日期', name: 'inventoryDate', index: 'inventory_date', width: 120},
            {label: '仓库', name: 'warehouseName', index: 'warehouse_code', width: 120},
            {label: '商品', name: 'materialDesc', index: 'material_code', width: 150},
            {label: '工程师', name: 'engineerName', index: 'engineer', width: 80},
            {label: '数量', name: 'qty', index: 'qty', width: 100},
            {label: '总金额', name: 'totalAmount', index: 'total_amount', width: 100},
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
        wmsTransferOrderAll: {},
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
        getInfo: function (warehouseCode) {
            $.get(baseURL + "wmstransferorderall/info/" + warehouseCode, function (r) {
                vm.wmsTransferOrderAll = r.wmsTransferOrderAll;
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
                var node = wh_ztree.getNodeByParam("warehouseCode", vm.wmsTransferOrderAll.warehouseCode);
                if (node != null) {
                    wh_ztree.selectNode(node);

                    vm.wmsTransferOrderAll.warehouseName = node.warehouseName;
                }
            })
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
                    vm.q.warehouseCode = r.page.list[0].warehouseCode;
                    vm.q.warehouseName = r.page.list[0].warehouseName;
                    // }
                } else {
                    alert(r.msg);
                }
            })
        }
    }
});

vm.getWarehouse();
vm.getDefalutWarehouse();

getPubMaterialSelect2($("#materialCodeQ"));
getEngineerSelect2($("#engineerQ"));

vm.q.inventoryDate = getCurrentDate();