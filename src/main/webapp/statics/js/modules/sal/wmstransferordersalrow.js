$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferordersalrow/list',
        datatype: "json",
        colModel: [
			{ label: 'toSalRowId', name: 'toSalRowId', index: 'to_sal_row_id', width: 50, key: true },
			{ label: '转储单日期', name: 'toDate', index: 'to_date', width: 80 }, 
			{ label: '转储单号', name: 'toNo', index: 'to_no', width: 80 }, 
			{ label: '转储单序号', name: 'toSeq', index: 'to_seq', width: 80 }, 
			{ label: '物料代码', name: 'materialCode', index: 'material_code', width: 80 }, 
			{ label: '指导单价', name: 'guidanceUnitPrice', index: 'guidance_unit_price', width: 80 }, 
			{ label: '单价', name: 'unitPrice', index: 'unit_price', width: 80 }, 
			{ label: '数量', name: 'qty', index: 'qty', width: 80 }, 
			{ label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80 }, 
			{ label: '移动类型', name: 'moveTypeCode', index: 'move_type_code', width: 80 }, 
			{ label: '状态', name: 'dataStatus', index: 'data_status', width: 80 }, 
			{ label: '所属工程师', name: 'engineer', index: 'engineer', width: 80 }, 
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
		wmsTransferOrderSalRow: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrderSalRow = {};
		},
		update: function (event) {
			var toSalRowId = getSelectedRow();
			if(toSalRowId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(toSalRowId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrderSalRow.toSalRowId == null ? "wmstransferordersalrow/save" : "wmstransferordersalrow/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsTransferOrderSalRow),
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
			var toSalRowIds = getSelectedRows();
			if(toSalRowIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmstransferordersalrow/delete",
				    contentType: "application/json",
				    data: JSON.stringify(toSalRowIds),
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
		getInfo: function(toSalRowId){
			$.get(baseURL + "wmstransferordersalrow/info/"+toSalRowId, function(r){
                vm.wmsTransferOrderSalRow = r.wmsTransferOrderSalRow;
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