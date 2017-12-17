$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'purmaterialsupplierprice/list',
        datatype: "json",
        colModel: [
			{ label: '编号', name: 'mspId', index: 'msp_id', width: 50, key: true },
			{ label: '物料代码', name: 'materialCode', index: 'material_code', width: 80 }, 
			{ label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 80 }, 
			{ label: '采购单价', name: 'purchaseUnitPrice', index: 'purchase_unit_price', width: 80 }, 
			{ label: '合同开始日期', name: 'contractStartDate', index: 'contract_start_date', width: 80 }, 
			{ label: '合同截止日期', name: 'contractEndDate', index: 'contract_end_date', width: 80 }, 
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
		purMaterialSupplierPrice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.purMaterialSupplierPrice = {};
		},
		update: function (event) {
			var mspId = getSelectedRow();
			if(mspId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(mspId)
		},
		saveOrUpdate: function (event) {
			var url = vm.purMaterialSupplierPrice.mspId == null ? "purmaterialsupplierprice/save" : "purmaterialsupplierprice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.purMaterialSupplierPrice),
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
			var mspIds = getSelectedRows();
			if(mspIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "purmaterialsupplierprice/delete",
				    contentType: "application/json",
				    data: JSON.stringify(mspIds),
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
		getInfo: function(mspId){
			$.get(baseURL + "purmaterialsupplierprice/info/"+mspId, function(r){
                vm.purMaterialSupplierPrice = r.purMaterialSupplierPrice;
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