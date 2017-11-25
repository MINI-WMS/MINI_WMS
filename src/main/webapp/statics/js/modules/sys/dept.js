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
		dept: {
			parentDeptName: null,
			parentDeptId: 0,
			orderNum: 0
		}
	},
	methods: {
		getDept: function () {
			//加载部门树
			$.get(baseURL + "sys/dept/select", function (r) {
				ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
				var node = ztree.getNodeByParam("deptId", vm.dept.parentDeptId);
				ztree.selectNode(node);

				vm.dept.parentDeptName = node.deptName;
			})
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.dept = {parentDeptName: null, parentDeptId: 0, orderNum: 0};
			vm.getDept();
		},
		update: function () {
			var deptId = getDeptId();
			if (deptId == null) {
				return;
			}

			$.get(baseURL + "sys/dept/info/" + deptId, function (r) {
				vm.showList = false;
				vm.title = "修改";
				vm.dept = r.dept;

				vm.getDept();
			});
		},
		del: function () {
			var deptId = getDeptId();
			if (deptId == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "sys/dept/delete",
					data: "deptId=" + deptId,
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
		saveOrUpdate: function (event) {
			var url = vm.dept.deptId == null ? "sys/dept/save" : "sys/dept/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.dept),
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
					vm.dept.parentDeptId = node[0].deptId;
					vm.dept.parentDeptName = node[0].deptName;

					layer.close(index);
				}
			});
		},
		reload: function () {
			vm.showList = true;
			Dept.table.refresh();
		}
	}
});

var Dept = {
	id: "deptTable",
	table: null,
	layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
	var columns = [
		{field: 'selectItem', radio: true},
		{title: '上级机构', field: 'parentDeptName', align: 'center', valign: 'middle', sortable: true, width: '80px'},
		{title: '机构名称', field: 'deptName', align: 'center', valign: 'middle', sortable: true, width: '130px'},
		{
			title: '类型', field: 'deptType', align: 'center', valign: 'middle', sortable: true, width: '60px',
			formatter: function (item, index) {
				if (item.deptType === 1) {
					return '<span class="label label-primary">集团</span>';
				}
				if (item.deptType === 2) {
					return '<span class="label label-info">公司</span>';
				}
				if (item.deptType === 3) {
					return '<span class="label label-warning">部门</span>';
				}
				if (item.deptType === 4) {
					return '<span class="label label-warning">车间</span>';
				}
				if (item.deptType === 5) {
					return '<span class="label label-warning">班组</span>';
				}
			}
		},
		{title: '机构地址', field: 'deptAddr', align: 'center', valign: 'middle', sortable: true, width: '150px'},
		{title: '排序', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '50px'},
		{
			title: '状态', field: 'isEnabled', align: 'center', valign: 'middle', sortable: true, width: '55px',
			formatter: function (item, index) {
				return item.isEnabled === 0 ?
					'<span class="label label-danger">禁用</span>' :
					'<span class="label label-success">正常</span>';
			}
		},
		{title: '修改用户', field: 'modifierName', align: 'center', valign: 'middle', sortable: true, width: '80px'},
		{title: '修改时间', field: 'modifyDate', align: 'center', valign: 'middle', sortable: true, width: '150px'},
		{title: '创建用户', field: 'creatorName', align: 'center', valign: 'middle', sortable: true, width: '80px'},
		{title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true, width: '150px'}]
	return columns;
};


function getDeptId() {
	var selected = $('#deptTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
		alert("请选择一条记录");
		return false;
	} else {
		return selected[0].id;
	}
}


$(function () {
	$.get(baseURL + "sys/dept/info", function (r) {
		var colunms = Dept.initColumn();
		var table = new TreeTable(Dept.id, baseURL + "sys/dept/list", colunms);
		table.setRootCodeValue(r.deptId);
		table.setExpandColumn(2);
		table.setIdField("deptId");
		table.setCodeField("deptId");
		table.setParentCodeField("parentDeptId");
		table.setExpandAll(false);
		table.init();
		Dept.table = table;
	});
});
