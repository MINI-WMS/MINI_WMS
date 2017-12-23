$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmstransferordersal/list',
        datatype: "json",
        colModel: [
			{ label: 'toSalId', name: 'toSalId', index: 'to_sal_id', width: 50, key: true },
			{ label: '转储单日期', name: 'toDate', index: 'to_date', width: 80 }, 
			{ label: '转储单号', name: 'toNo', index: 'to_no', width: 80 }, 
			{ label: '客户代码', name: 'customerCode', index: 'customer_code', width: 80 }, 
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
		wmsTransferOrderSal: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrderSal = {};
		},
		update: function (event) {
			var toSalId = getSelectedRow();
			if(toSalId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(toSalId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrderSal.toSalId == null ? "wmstransferordersal/save" : "wmstransferordersal/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsTransferOrderSal),
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
			var toSalIds = getSelectedRows();
			if(toSalIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmstransferordersal/delete",
				    contentType: "application/json",
				    data: JSON.stringify(toSalIds),
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
		getInfo: function(toSalId){
			$.get(baseURL + "wmstransferordersal/info/"+toSalId, function(r){
                vm.wmsTransferOrderSal = r.wmsTransferOrderSal;
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