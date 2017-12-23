$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'pursupplier/list',
		datatype: "json",
		colModel: [
			{label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 100, key: true},
			{label: '供应商名称', name: 'supplierName', index: 'supplier_name', width: 130},
			{label: '供应商类型', name: 'supplierTypeDesc', index: 'supplier_type_code', width: 80},
			{label: '地区', name: 'areaName', index: 'area_code', width: 100},
			{label: '联系人姓名', name: 'contactName', index: 'contact_name', width: 90},
			{label: '联系人电话', name: 'contactPhone', index: 'contact_phone', width: 120},
			{label: '税务登记证号', name: 'taxRegistrationCertificate', index: 'tax_registration_certificate', width: 120},
			{label: '供应商地址', name: 'supplierAddr', index: 'supplier_addr', width: 180},
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
		rowList: [10, 30, 50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth: true,
		multiselect: true,
		shrinkToFit: false,
		pager: "#jqGridPager",
		jsonReader: {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames: {
			page: "page",
			rows: "limit",
			order: "order"
		},
		gridComplete: function () {
			$(window).resize();
			//隐藏grid底部滚动条
			// $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
		}
	});
});

var vm = new Vue({
	el: '#familyApp',
	data: {
		showList: true,
		title: null,
		purSupplier: {},
		addNew: false
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.purSupplier = {};
			vm.addNew = true;
		},
		update: function (event) {
			var supplierCode = getSelectedRow();
			if (supplierCode == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(supplierCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "pursupplier/save" : "pursupplier/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.purSupplier),
				success: function (r) {
					if (r.code === 0) {
						alert('操作成功', function (index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var supplierCodes = getSelectedRows();
			if (supplierCodes == null) {
				return;
			}

			confirm('确定要禁用选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "pursupplier/delete",
					contentType: "application/json",
					data: JSON.stringify(supplierCodes),
					success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function (supplierCode) {
			$.get(baseURL + "pursupplier/info/" + supplierCode, function (r) {
				vm.purSupplier = r.purSupplier;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		}
	}
});