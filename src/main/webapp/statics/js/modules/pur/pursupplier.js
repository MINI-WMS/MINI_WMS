$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pursupplier/list',
        datatype: "json",
        colModel: [
			{ label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 50, key: true },
			{ label: '供应商名称', name: 'supplierName', index: 'supplier_name', width: 80 }, 
			{ label: '供应商类型代码', name: 'supplierTypeCode', index: 'supplier_type_code', width: 80 }, 
			{ label: '地区代码', name: 'areaCode', index: 'area_code', width: 80 }, 
			{ label: '供应商地址', name: 'supplierAddr', index: 'supplier_addr', width: 80 }, 
			{ label: '联系人姓名', name: 'contactName', index: 'contact_name', width: 80 }, 
			{ label: '联系人电话', name: 'contactPhone', index: 'contact_phone', width: 80 }, 
			{ label: '税务登记证号', name: 'taxRegistrationCertificate', index: 'tax_registration_certificate', width: 80 }, 
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
		purSupplier: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.purSupplier = {};
		},
		update: function (event) {
			var supplierCode = getSelectedRow();
			if(supplierCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(supplierCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.purSupplier.supplierCode == null ? "pursupplier/save" : "pursupplier/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.purSupplier),
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
			var supplierCodes = getSelectedRows();
			if(supplierCodes == null){
				return ;
			}

			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "pursupplier/delete",
				    contentType: "application/json",
				    data: JSON.stringify(supplierCodes),
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
		getInfo: function(supplierCode){
			$.get(baseURL + "pursupplier/info/"+supplierCode, function(r){
                vm.purSupplier = r.purSupplier;
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