$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pursuppliertype/list',
        datatype: "json",
        colModel: [
			{ label: '供应商类型代码', name: 'supplierTypeCode', index: 'supplier_type_code', width: 50, key: true },
			{ label: '供应商类型描述', name: 'supplierTypeDesc', index: 'supplier_type_desc', width: 80 },
			{ label: '状态', name: 'isEnabled', index: 'is_enabled', width: 80 }, 
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
		purSupplierType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.purSupplierType = {};
		},
		update: function (event) {
			var supplierTypeCode = getSelectedRow();
			if(supplierTypeCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(supplierTypeCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.purSupplierType.supplierTypeCode == null ? "pursuppliertype/save" : "pursuppliertype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.purSupplierType),
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
			var supplierTypeCodes = getSelectedRows();
			if(supplierTypeCodes == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "pursuppliertype/delete",
				    contentType: "application/json",
				    data: JSON.stringify(supplierTypeCodes),
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
		getInfo: function(supplierTypeCode){
			$.get(baseURL + "pursuppliertype/info/"+supplierTypeCode, function(r){
                vm.purSupplierType = r.purSupplierType;
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