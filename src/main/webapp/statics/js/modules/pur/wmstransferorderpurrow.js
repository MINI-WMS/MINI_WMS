$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferorderpurrow/list',
        datatype: "json",
        colModel: [
			{ label: 'toPurRowId', name: 'toPurRowId', index: 'to_pur_row_id', width: 50, key: true },
			{ label: '转储单日期', name: 'toDate', index: 'to_date', width: 80 }, 
			{ label: '转储单号', name: 'toNo', index: 'to_no', width: 80 }, 
			{ label: '转储单序号', name: 'toSeq', index: 'to_seq', width: 80 }, 
			{ label: '仓库代码', name: 'warehouseCode', index: 'warehouse_code', width: 80 }, 
			{ label: '物料代码', name: 'materialCode', index: 'material_code', width: 80 }, 
			{ label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 80 }, 
			{ label: '指导单价', name: 'guidanceUnitPrice', index: 'guidance_unit_price', width: 80 }, 
			{ label: '单价', name: 'unitPrice', index: 'unit_price', width: 80 }, 
			{ label: '数量', name: 'qty', index: 'qty', width: 80 }, 
			{ label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80 }, 
			{ label: '移动类型', name: 'moveTypeCode', index: 'move_type_code', width: 80 }, 
			{ label: '所属工程师', name: 'engineer', index: 'engineer', width: 80 },
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
		wmsTransferOrderPurRow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrderPurRow = {};
		},
		update: function (event) {
			var toPurRowId = getSelectedRow();
			if(toPurRowId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(toPurRowId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrderPurRow.toPurRowId == null ? "wmstransferorderpurrow/save" : "wmstransferorderpurrow/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsTransferOrderPurRow),
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
			var toPurRowIds = getSelectedRows();
			if(toPurRowIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmstransferorderpurrow/delete",
				    contentType: "application/json",
				    data: JSON.stringify(toPurRowIds),
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
		getInfo: function(toPurRowId){
			$.get(baseURL + "wmstransferorderpurrow/info/"+toPurRowId, function(r){
                vm.wmsTransferOrderPurRow = r.wmsTransferOrderPurRow;
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