$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'salcustomermaterialprice/list',
        datatype: "json",
        colModel: [
			{ label: 'mcpId', name: 'mcpId', index: 'mcp_id', width: 50, key: true },
			{ label: '商品代码', name: 'materialCode', index: 'material_code', width: 80 },
			{ label: '客户代码', name: 'customerCode', index: 'customer_code', width: 80 }, 
			{ label: '销售单价', name: 'saleUnitPrice', index: 'sale_unit_price', width: 80 }, 
			{ label: '开始日期', name: 'contractStartDate', index: 'contract_start_date', width: 80 }, 
			{ label: '截止日期', name: 'contractEndDate', index: 'contract_end_date', width: 80 },
			{
				label: '状态', name: 'isEnabled', width: 60, index: 'is_enabled', formatter: function (value, options, row) {
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
		salCustomerMaterialPrice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.salCustomerMaterialPrice = {};
		},
		update: function (event) {
			var mcpId = getSelectedRow();
			if(mcpId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(mcpId)
		},
		saveOrUpdate: function (event) {
			var url = vm.salCustomerMaterialPrice.mcpId == null ? "salcustomermaterialprice/save" : "salcustomermaterialprice/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.salCustomerMaterialPrice),
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
			var mcpIds = getSelectedRows();
			if(mcpIds == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "salcustomermaterialprice/delete",
				    contentType: "application/json",
				    data: JSON.stringify(mcpIds),
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
		getInfo: function(mcpId){
			$.get(baseURL + "salcustomermaterialprice/info/"+mcpId, function(r){
                vm.salCustomerMaterialPrice = r.salCustomerMaterialPrice;
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