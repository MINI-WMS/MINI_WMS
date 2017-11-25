var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "parentMenuId",
			rootPId: -1
		},
		key: {
			name: "menuName",
			url: "nourl"
		}
	}
};
var ztree;

var vm = new Vue({
	el: '#familyApp',
	data: {
		q: {
			isEnabled: ""
		},
		showList: true,
		title: null,
		menu: {
			parentMenuName: null,
			parentMenuId: 0,
			menuType: 1,
			orderNum: 0
		}
	},
	methods: {
		getMenu: function (menuId) {
			//加载菜单树
			$.get(baseURL + "sys/menu/select", function (r) {
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentMenuId);
				ztree.selectNode(node);

				vm.menu.parentMenuName = node.menuName;
			})
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.menu = {parentMenuName: null, parentMenuId: 0, menuType: 1, orderNum: 0};
			vm.getMenu();
		},
		update: function () {
			var menuId = getMenuId();
			if (menuId == null) {
				return;
			}

			$.get(baseURL + "sys/menu/info/" + menuId, function (r) {
				vm.showList = false;
				vm.title = "修改";
				vm.menu = r.menu;

				vm.getMenu();
			});
		},
		del: function () {
			var menuId = getMenuId();
			if (menuId == null) {
				return;
			}

			confirm('确定要禁用选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/menu/delete",
					data: "menuId=" + menuId,
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
			});
		},
		saveOrUpdate: function () {
			if (vm.validator()) {
				return;
			}

			var url = vm.menu.menuId == null ? "sys/menu/save" : "sys/menu/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.menu),
				success: function (r) {
					if (r.code === 0) {
						msg('操作成功');
						vm.reload();
					} else {
						alert(r.msg);
					}
				}
			});
		},
		menuTree: function () {
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.menu.parentMenuId = node[0].menuId;
					vm.menu.parentMenuName = node[0].menuName;

					layer.close(index);
				}
			});
		},
		reload: function () {
			vm.showList = true;
			// Menu.table.dataUrl = baseURL + "sys/menu/list?isEnabled:" + vm.q.isEnabled;
			Menu.table.refresh();
		},
		validator: function () {
			if (isBlank(vm.menu.menuName)) {
				alert("菜单名称不能为空");
				return true;
			}

			//菜单
			if (vm.menu.menuType === 1 && isBlank(vm.menu.url)) {
				alert("菜单URL不能为空");
				return true;
			}
		}
	}
});


var Menu = {
	id: "menuTable",
	table: null,
	layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
	var columns = [
		{field: 'selectItem', radio: true},
		{title: '上级菜单', field: 'parentMenuName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
		{title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle', sortable: true, width: '180px'},
		// {title: '', field: 'menuId', align: 'center', valign: 'middle', width: '0px',hidden:true,hidedlg:true},
		{
			title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '50px',
			formatter: function (item, index) {
				return item.icon == null ? '' : '<i class="' + item.icon + ' fa-lg"></i>';
			}
		},
		{
			title: '类型', field: 'menuType', align: 'center', valign: 'middle', sortable: true, width: '55px',
			formatter: function (item, index) {
				if (item.menuType === 0) {
					return '<span class="label label-primary">目录</span>';
				}
				if (item.menuType === 1) {
					return '<span class="label label-info">菜单</span>';
				}
				if (item.menuType === 2) {
					return '<span class="label label-warning">按钮</span>';
				}
			}
		},
		{title: '排序', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '50px'},
		{title: '菜单URL', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '160px'},
		{title: '授权标识', field: 'perms', align: 'center', valign: 'middle', sortable: true, width: '100px'},
		{
			title: '状态', field: 'isEnabled', align: 'center', valign: 'middle', sortable: true, width: '60px',
			formatter: function (item, index) {
				return item.isEnabled === 0 ?
					'<span class="label label-danger">禁用</span>' :
					'<span class="label label-success">正常</span>';
			}
		},
		{title: '修改用户', field: 'modifierName', align: 'center', valign: 'middle', sortable: true, width: '80px'},
		{title: '修改时间', field: 'modifyDate', align: 'center', valign: 'middle', sortable: true, width: '120px'},
		{title: '创建用户', field: 'creatorName', align: 'center', valign: 'middle', sortable: true, width: '80px'},
		{title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '130px'}]
	return columns;
};


function getMenuId() {
	var selected = $('#menuTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
		alert("请选择一条记录");
		return false;
	} else {
		return selected[0].id;
	}
}


$(function () {
	var colunms = Menu.initColumn();
	var table = new TreeTable(Menu.id, baseURL + "sys/menu/list?isEnabled:" + vm.q.isEnabled, colunms);
	table.setExpandColumn(2);
	table.setIdField("menuId");
	table.setCodeField("menuId");
	table.setParentCodeField("parentMenuId");
	table.setExpandAll(false);
	table
	table.init();
	Menu.table = table;
});
