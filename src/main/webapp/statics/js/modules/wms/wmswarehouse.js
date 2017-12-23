$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'wmswarehouse/list',
		datatype: "json",
		colModel: [
			{label: '仓库代码', name: 'warehouseCode', index: 'warehouse_code', width: 100, key: true},
			{label: '仓库名称', name: 'warehouseName', index: 'warehouse_name', width: 150},
			{label: '所属机构', name: 'deptName', index: 'dept_id', width: 120},
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

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "deptId",
			pIdKey: "parentDeptId",
			rootPId: -1
		},
		key: {
			name: "deptName",
			url: "nourl"
		}
	}
};
var ztree;

var vm = new Vue({
	el: '#familyApp',
	data: {
		showList: true,
		title: null,
		addNew: false,
		wmsWarehouse: {
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsWarehouse = {deptId: 0,
				deptName: ""};

			vm.getDept();
		},
		getDept: function () {
			//加载部门树
			$.get(baseURL + "sys/dept/list", function (r) {
				ztree = $.fn.zTree.init($("#deptTree"), setting, r);
				var node = ztree.getNodeByParam("deptId", vm.wmsWarehouse.deptId);
				if (node != null) {
					ztree.selectNode(node);

					vm.wmsWarehouse.deptName = node.deptName;
				}
			})
		},
		update: function (event) {
			var warehouseCode = getSelectedRow();
			if (warehouseCode == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(warehouseCode)

		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "wmswarehouse/save" : "wmswarehouse/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsWarehouse),
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
			var warehouseCodes = getSelectedRows();
			if (warehouseCodes == null) {
				return;
			}

			confirm('确定要禁用选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmswarehouse/delete",
					contentType: "application/json",
					data: JSON.stringify(warehouseCodes),
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
		getInfo: function (warehouseCode) {
			$.get(baseURL + "wmswarehouse/info/" + warehouseCode, function (r) {
				vm.wmsWarehouse = r.wmsWarehouse;
				vm.getDept();
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		},
		deptTree: function () {
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择部门",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#deptLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级部门
					vm.wmsWarehouse.deptId = node[0].deptId;
					vm.wmsWarehouse.deptName = node[0].deptName;

					layer.close(index);
				}
			});
		}
	}
});

$(window).resize();