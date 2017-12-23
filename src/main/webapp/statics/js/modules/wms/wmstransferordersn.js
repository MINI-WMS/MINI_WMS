$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferordersn/list',
        datatype: "json",
        colModel: [
			{ label: 'colName', name: 'toSnId', index: 'to_sn_id', width: 80 }, 
			{ label: '转储单日期', name: 'toDate', index: 'to_date', width: 80 }, 
			{ label: '转储单号', name: 'toNo', index: 'to_no', width: 80 }, 
			{ label: '转储单序号', name: 'toSeq', index: 'to_seq', width: 80 }, 
			{ label: 'materialCode', name: 'materialCode', index: 'material_code', width: 50, key: true },
			{ label: '物料SN', name: 'materialSn', index: 'material_sn', width: 80 },
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
		wmsTransferOrderSn: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrderSn = {};
		},
		update: function (event) {
			var materialCode = getSelectedRow();
			if(materialCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(materialCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "wmstransferordersn/save" : "wmstransferordersn/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsTransferOrderSn),
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
			var materialCodes = getSelectedRows();
			if(materialCodes == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmstransferordersn/delete",
				    contentType: "application/json",
				    data: JSON.stringify(materialCodes),
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
		getInfo: function(materialCode){
			$.get(baseURL + "wmstransferordersn/info/"+materialCode, function(r){
                vm.wmsTransferOrderSn = r.wmsTransferOrderSn;
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