$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmswarehouseinventory/list',
        datatype: "json",
        colModel: [
			{ label: 'wiId', name: 'wiId', index: 'wi_id', width: 50, key: true },
			{ label: '结存日期', name: 'inventoryDate', index: 'inventory_date', width: 80 }, 
			{ label: '仓库编号', name: 'warehouseCode', index: 'warehouse_code', width: 80 }, 
			{ label: '物料代码', name: 'materialCode', index: 'material_code', width: 80 }, 
			{ label: '数量', name: 'qty', index: 'qty', width: 80 }, 
			{ label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80 },
			{
				label: '状态', name: 'dataStatus', width: 60, index: 'dataStatus', formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">禁用</span>' :
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
		wmsWarehouseInventory: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsWarehouseInventory = {};
		},
		update: function (event) {
			var wiId = getSelectedRow();
			if(wiId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(wiId)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "wmswarehouseinventory/save" : "wmswarehouseinventory/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsWarehouseInventory),
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
			var wiIds = getSelectedRows();
			if(wiIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmswarehouseinventory/delete",
				    contentType: "application/json",
				    data: JSON.stringify(wiIds),
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
		getInfo: function(wiId){
			$.get(baseURL + "wmswarehouseinventory/info/"+wiId, function(r){
                vm.wmsWarehouseInventory = r.wmsWarehouseInventory;
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