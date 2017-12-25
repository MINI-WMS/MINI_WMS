$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferorder/list',
        datatype: "json",
        colModel: [
			{ label: '转储单编号', name: 'toId', index: 'to_id', width: 80 }, 
			{ label: '转储单日期', name: 'toDate', index: 'to_date', width: 80 }, 
			{ label: '转储单号', name: 'toNo', index: 'to_no', width: 80 }, 
			{ label: 'warehouseCode', name: 'warehouseCode', index: 'warehouse_code', width: 50, key: true },
			{ label: '来源仓库', name: 'sourWarehouseCode', index: 'sour_warehouse_code', width: 80 }, 
			{ label: '目标仓库', name: 'destWarehouseCode', index: 'dest_warehouse_code', width: 80 }, 
			{ label: '状态', name: 'dataStatus', index: 'data_status', width: 80 }, 
			{ label: '创建用户', name: 'creatorId', index: 'creator_id', width: 80 }, 
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }, 
			{ label: '修改用户', name: 'modifierId', index: 'modifier_id', width: 80 }, 
			{ label: '修改时间', name: 'modifyDate', index: 'modify_date', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
		shrinkToFit: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
        	$(window).resize();
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#familyApp',
	data:{
		showList: true,
		title: null,
		addNew: false,
		wmsTransferOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrder = {};
		},
		update: function (event) {
			var warehouseCode = getSelectedRow();
			if(warehouseCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(warehouseCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrder.warehouseCode == null ? "wmstransferorder/save" : "wmstransferorder/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsTransferOrder),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var warehouseCodes = getSelectedRows();
			if(warehouseCodes == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmstransferorder/delete",
				    contentType: "application/json",
				    data: JSON.stringify(warehouseCodes),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(warehouseCode){
			$.get(baseURL + "wmstransferorder/info/"+warehouseCode, function(r){
                vm.wmsTransferOrder = r.wmsTransferOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	}
});