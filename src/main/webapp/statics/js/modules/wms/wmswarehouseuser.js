$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'wmswarehouseuser/list',
		datatype: "json",
		colModel: [
			{label: 'wmsUserId', name: 'wmsUserId', index: 'wms_user_id', width: 50, key: true, hidden: true},
			{label: '用户', name: 'username', index: 'user_id', width: 80},
			{label: '仓库', name: 'warehouseName', index: 'warehouse_code', width: 80},
			{label: '创建用户', name: 'creatorName', index: 'creator_id', width: 80},
			{label: '创建时间', name: 'createDate', index: 'create_date', width: 150}
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

//部门结构树
var dept_ztree;
var dept_setting = {
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
//用户树
var user_ztree;
var user_setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "userId",
			pIdKey: "",
			rootPId: -1
		},
		key: {
			name: "username",
			url: "nourl"
		}
	},
	check: {
		enable: true,
		nocheckInherit: true,
		chkboxType: {"Y": "", "N": ""}
	}
};

//数据树
var data_ztree;
var data_setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "warehouseCode",
			pIdKey: "",
			rootPId: -1
		},
		key: {
			name: "warehouseName",
			url: "nourl"
		}
	},
	check: {
		enable: true,
		nocheckInherit: true,
		chkboxType: {"Y": "", "N": ""}
	}
};

var vm = new Vue({
	el: '#familyApp',
	data: {
		showList: true,
		title: null,
		addNew: false,
		wmsWarehouseUser: {},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsWarehouseUser = {deptName: null, deptId: null, userId: null, username: null};

			vm.getDept();

			vm.getWarehouseTree();
		},
		update: function (event) {
			var wmsUserId = getSelectedRow();
			if (wmsUserId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;


			vm.getInfo(wmsUserId)
		},
		saveOrUpdate: function (event) {
			//获取选择的数据
			var nodes = data_ztree.getCheckedNodes(true);
			var warehouseCodeList = new Array();
			for (var i = 0; i < nodes.length; i++) {
				warehouseCodeList.push(nodes[i].warehouseCode);
			}
			vm.wmsWarehouseUser.warehouseCodeList = warehouseCodeList;

			// var url = vm.addNew ? "wmswarehouseuser/save" : "wmswarehouseuser/update";
			var url = "wmswarehouseuser/save";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsWarehouseUser),
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
			var wmsUserIds = getSelectedRows();
			if (wmsUserIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmswarehouseuser/delete",
					contentType: "application/json",
					data: JSON.stringify(wmsUserIds),
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
		getInfo: function (wmsUserId) {
			$.get(baseURL + "wmswarehouseuser/info/" + wmsUserId, function (r) {
				vm.wmsWarehouseUser = r.wmsWarehouseUser;

				vm.getDept();
				vm.getWarehouseTree();
				vm.getUser();
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		},
		getWarehouseTree: function (roleId) {
			//加载数据
			$.get(baseURL + "wmswarehouse/list?isEnabled=1", function (r) {
				data_ztree = $.fn.zTree.init($("#dataTree"), data_setting, r.page.list);
				//展开所有节点
				// data_ztree.expandAll(true);

				//勾选角色所拥有的部门数据权限
				var warehouseCodeList = vm.wmsWarehouseUser.warehouseCodeList;
				if(warehouseCodeList === undefined)return;
				for (var i = 0; i < warehouseCodeList.length; i++) {
					var node = data_ztree.getNodeByParam("warehouseCode", warehouseCodeList[i]);
					data_ztree.checkNode(node, true, false);
				}

			});
		},
		getDept: function () {
			//加载部门树
			$.get(baseURL + "sys/dept/list?isEnabled=1", function (r) {
				dept_ztree = $.fn.zTree.init($("#deptTree"), dept_setting, r);
				var node = dept_ztree.getNodeByParam("deptId", vm.wmsWarehouseUser.deptId);
				if (node != null) {
					dept_ztree.selectNode(node);

					vm.wmsWarehouseUser.deptName = node.deptName;
				}
			})
		},
		getUser: function () {
			//加载用户
			var deptIdParam = vm.wmsWarehouseUser.deptId === null ? "" : ("&deptId=" + vm.wmsWarehouseUser.deptId);
			$.get(baseURL + "sys/user/list?isEnabled=1" + deptIdParam, function (r) {
				user_ztree = $.fn.zTree.init($("#userTree"), user_setting, r.page.list);
				var node = user_ztree.getNodeByParam("userId", vm.wmsWarehouseUser.userId);
				if (node != null) {
					user_ztree.selectNode(node);

					vm.wmsWarehouseUser.username = node.username;
				}
			})
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
					var node = dept_ztree.getSelectedNodes();
					if(node === null){
						vm.wmsWarehouseUser.deptId = null;
						vm.wmsWarehouseUser.deptName = null;

						layer.close(index);
						return;
					}
					//选择上级部门
					vm.wmsWarehouseUser.deptId = node[0].deptId;
					vm.wmsWarehouseUser.deptName = node[0].deptName;

					layer.close(index);

					vm.getUser();
				}
			});
		},
		userTree: function () {
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择用户",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#userLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = user_ztree.getSelectedNodes();
					if(node === null) {
						vm.wmsWarehouseUser.userId = null;
						vm.wmsWarehouseUser.username = null;
						layer.close(index);
						return;
					}
					//选择用户
					vm.wmsWarehouseUser.userId = node[0].userId;
					vm.wmsWarehouseUser.username = node[0].username;

					layer.close(index);
				}
			});
		}
	}
});