$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pubmaterial/list',
        datatype: "json",
        colModel: [
			{ label: '物料代码', name: 'materialCode', index: 'material_code', width: 80, key: true },
			{ label: '物料描述', name: 'materialDesc', index: 'material_desc', width: 110 },
			{ label: '物料类型', name: 'materialTypeDesc', index: 'material_type_code', width: 80 },
			{ label: '库存单位', name: 'inventoryUnit', index: 'inventory_unit', width: 80 }, 
			{ label: '采购单位', name: 'purchaseUnit', index: 'purchase_unit', width: 80 }, 
			{ label: '销售单位', name: 'saleUnit', index: 'sale_unit', width: 80 }, 
			{ label: '采购库存单位转换比', name: 'purchaseToInventory', index: 'purchase_to_inventory', width: 150 },
			{ label: '销售库存单位转换比', name: 'saleToInventory', index: 'sale_to_inventory', width: 150 },
			{ label: '税率', name: 'taxRate', index: 'tax_rate', width: 80 },
			{
				label: '状态', name: 'isEnabled', width: 60, index: 'is_enabled', formatter: function (value, options, row) {
				return value === 0 ?
					'<span class="label label-danger">禁用</span>' :
					'<span class="label label-success">正常</span>';
			}
			},
			{ label: '创建用户', name: 'creatorName', index: 'creator_id', width: 80 },
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 150 },
			{ label: '修改用户', name: 'modifierName', index: 'modifier_id', width: 80 },
			{ label: '修改时间', name: 'modifyDate', index: 'modify_date', width: 150 }
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
        	// $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#familyApp',
	data:{
		q:{
			materialCode:null,
			materialDesc:null,
			isEnabled:1
		},
		showList: true,
		title: null,
		addNew:false,
		pubMaterial: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.pubMaterial = {};
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
			var url = vm.addNew ? "pubmaterial/save" : "pubmaterial/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.pubMaterial),
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

			confirm('确定要禁用选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "pubmaterial/delete",
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
			$.get(baseURL + "pubmaterial/info/"+materialCode, function(r){
                vm.pubMaterial = r.pubMaterial;
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