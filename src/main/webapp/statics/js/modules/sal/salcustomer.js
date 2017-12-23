$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'salcustomer/list',
		datatype: "json",
		colModel: [
			{label: '客户代码', name: 'customerCode', index: 'customer_code', width: 100, key: true},
			{label: '客户名称', name: 'customerName', index: 'customer_name', width: 120},
			{label: '客户类型', name: 'customerTypeDesc', index: 'customer_type_code', width: 100},
			{label: '地区', name: 'areaName', index: 'area_code', width: 80},
			{label: '客户地址', name: 'customerAddr', index: 'customer_addr', width: 150},
			{label: '联系人姓名', name: 'contactName', index: 'contact_name', width: 100},
			{label: '联系人电话', name: 'contactPhone', index: 'contact_phone', width: 120},
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
		addNew: false,
		salCustomer: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.salCustomer = {};
		},
		update: function (event) {
			var customerCode = getSelectedRow();
			if (customerCode == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(customerCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "salcustomer/save" : "salcustomer/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.salCustomer),
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
			var customerCodes = getSelectedRows();
			if (customerCodes == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "salcustomer/delete",
					contentType: "application/json",
					data: JSON.stringify(customerCodes),
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
		getInfo: function (customerCode) {
			$.get(baseURL + "salcustomer/info/" + customerCode, function (r) {
				vm.salCustomer = r.salCustomer;
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