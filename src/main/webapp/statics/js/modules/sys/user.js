$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'sys/user/list',
		datatype: "json",
		colModel: [
			{label: '用户ID', name: 'userId', index: 'user_id', width: 50, key: true},
			{label: '手机号', name: 'phone', width: 100},
			{label: '用户名称', name: 'username', width: 75},
			{label: '所属部门', name: 'deptName', index: 'dept_id', width: 75},
			{label: '邮箱', name: 'email', width: 150},
			{
				label: '类型', name: 'userType', index: 'user_type', width: 60, formatter: function (value, options, row) {
					if (value === 1) return '<span class="label label-success">用户</span>'
					if (value === 2) return '<span class="label label-danger">管理员</span>';
					if (value === 3) return '<span class="label label-success">工程师</span>';
					if (value === 4) return '<span class="label label-success">营业员</span>';
				}
			},
			{
				label: '状态', name: 'isEnabled', index: 'is_enabled', width: 60, formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">禁用</span>' :
						'<span class="label label-success">正常</span>';
				}
			},
			{label: '创建用户', name: 'creatorName', index: 'creator', width: 80},
			{label: '创建时间', name: 'createDate', index: 'create_date', width: 150},
			{label: '修改用户', name: 'modifierName', index: 'modifier', width: 80},
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
		q: {
			username: null
		},
		showList: true,
		title: null,
		roleList: {},
		user: {
			isEnabled: 1,
			deptId: 0,
			deptName: null,
			roleIdList: [],
			userType: 1
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {deptName: null, deptId: null, isEnabled: 1, roleIdList: []};

			//获取角色信息
			this.getRoleList();

			vm.getDept();
		},
		getDept: function () {
			//加载部门树
			$.get(baseURL + "sys/dept/list", function (r) {
				ztree = $.fn.zTree.init($("#deptTree"), setting, r);
				var node = ztree.getNodeByParam("deptId", vm.user.deptId);
				if (node != null) {
					ztree.selectNode(node);

					vm.user.deptName = node.deptName;
				}
			})
		},
		update: function () {
			var userId = getSelectedRow();
			if (userId == null) {
				return;
			}

			vm.showList = false;
			vm.title = "修改";

			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
		},
		del: function () {
			var userIds = getSelectedRows();
			if (userIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/user/delete",
					contentType: "application/json",
					data: JSON.stringify(userIds),
					success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function () {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function () {
			var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
			if (vm.user.password != null && vm.user.password.length > 0) {
				vm.user.password = hex_sha1(hex_sha1(vm.user.password))
			}
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.user),
				success: function (r) {
					if (r.code === 0) {
						alert('操作成功', function () {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		getUser: function (userId) {
			$.get(baseURL + "sys/user/info/" + userId, function (r) {
				vm.user = r.user;
				vm.user.password = null;

				vm.getDept();
			});
		},
		getRoleList: function () {
			$.get(baseURL + "sys/role/select", function (r) {
				vm.roleList = r.list;
			});
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
					vm.user.deptId = node[0].deptId;
					vm.user.deptName = node[0].deptName;

					layer.close(index);
				}
			});
		},
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {
					'username': vm.q.username,
					'phone': vm.q.phone
				},
				page: page
			}).trigger("reloadGrid");
		}
	}
});

$(window).resize();