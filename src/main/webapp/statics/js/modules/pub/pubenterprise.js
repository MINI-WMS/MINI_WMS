$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'pubenterprise/list',
		datatype: "json",
		colModel: [
			{label: '企业代码', name: 'enterpriseCode', index: 'enterprise_code', width: 80, key: true},
			{label: '企业描述', name: 'enterpriseDesc', index: 'enterprise_desc', width: 80},
			{label: '注册地址', name: 'registeredAddr', index: 'registered_addr', width: 120},
			{label: '数量小数位', name: 'qtyDecimal', index: 'qty_decimal', width: 80},
			{label: '金额小数位', name: 'amountDecimal', index: 'amount_decimal', width: 80},
			{label: '单价小数位', name: 'priceDecimal', index: 'price_decimal', width: 80},
			{label: '税务登记证号', name: 'taxRegistrationCertificate', index: 'tax_registration_certificate', width: 80},
			{label: '注册银行', name: 'registeredBank', index: 'registered_bank', width: 80},
			{label: '注册银行', name: 'bankAccount', index: 'bank_account', width: 80},
			{label: '排序', name: 'orderNum', index: 'order_num', width: 80},
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
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
		}
	});
});

var vm = new Vue({
	el: '#familyApp',
	data: {
		q: {
			isEnabled: 1
		},
		showList: true,
		title: null,
		addNew: false,
		pubEnterprise: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.pubEnterprise = {};
		},
		update: function (event) {
			var enterpriseCode = getSelectedRow();
			if (enterpriseCode == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(enterpriseCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "pubenterprise/save" : "pubenterprise/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.pubEnterprise),
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
			var enterpriseCodes = getSelectedRows();
			if (enterpriseCodes == null) {
				return;
			}

			confirm('确定要禁用选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "pubenterprise/delete",
					contentType: "application/json",
					data: JSON.stringify(enterpriseCodes),
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
		getInfo: function (enterpriseCode) {
			$.get(baseURL + "pubenterprise/info/" + enterpriseCode, function (r) {
				vm.pubEnterprise = r.pubEnterprise;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {'isEnabled': vm.q.isEnabled},
				page: page
			}).trigger("reloadGrid");
		}
	}
});