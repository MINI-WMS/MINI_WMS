$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'sys/config/list',
		datatype: "json",
		colModel: [
			{label: 'ID', name: 'configId', width: 30, sortable: true, key: true, hidden: true, hidedlg: true},
			{label: '参数名', name: 'configKey', width: 60, sortable: false},
			{label: '参数值', name: 'configValue', width: 100, sortable: false},
			{label: '备注', name: 'remark', width: 100, sortable: false},
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
		shrinkToFit: true,
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
			configKey: null,
			isEnabled: ""
		},
		showList: true,
		title: null,
		config: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.config = {};
		},
		update: function () {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}

			$.get(baseURL + "sys/config/info/" + id, function (r) {
				if (r.code === 0) {
					vm.showList = false;
					vm.title = "修改";
					vm.config = r.config;
				} else {
					alert(r.msg);
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if (ids == null) {
				return;
			}
			confirm('确定要禁用选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/config/delete",
					contentType: "application/json",
					data: JSON.stringify(ids),
					success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.config.configId == null ? "sys/config/save" : "sys/config/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.config),
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {'configKey': vm.q.configKey, 'isEnabled': vm.q.isEnabled},
				page: page
			}).trigger("reloadGrid");
		}
	}
});

