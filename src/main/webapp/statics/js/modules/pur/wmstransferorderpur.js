$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'wmstransferorderpur/list',
		datatype: "json",
		colModel: [
			{label: 'toPurId', name: 'toPurId', index: 'to_pur_id', width: 50, key: true, hidden: true},
			{label: '转储单日期', name: 'toDate', index: 'to_date', width: 120},
			{label: '转储单号', name: 'toNo', index: 'to_no', width: 150},
			{label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 120},
			{label: '供应商名称', name: 'supplierName', index: 'supplier_code', width: 150},
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
		showList: true,
		title: null,
		addNew: false,
		wmsTransferOrderPur: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrderPur = {};
		},
		update: function (event) {
			var toPurId = getSelectedRow();
			if (toPurId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = true;

			vm.getInfo(toPurId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrderPur.toPurId == null ? "wmstransferorderpur/save" : "wmstransferorderpur/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrderPur),
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
			var toPurIds = getSelectedRows();
			if (toPurIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferorderpur/delete",
					contentType: "application/json",
					data: JSON.stringify(toPurIds),
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
		getInfo: function (toPurId) {
			$.get(baseURL + "wmstransferorderpur/info/" + toPurId, function (r) {
				vm.wmsTransferOrderPur = r.wmsTransferOrderPur;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		},
		showRowList: function () {
			var toPurId = getSelectedRow();
			if (toPurId == null) {
				return;
			}

			var index = layer.open({
				type: 2,
				// skin: 'layui-layer-molv',
				title: "采购入库单明细",
				area: ['500px', '400px'],
				shadeClose: false,
				content: jQuery("#rowListLayer"),
				maxmin: true
			});
			layer.full(index);


			// var colunms = rowList.initColumn();
			// var table = new TreeTable(rowList.id, baseURL + "wmstransferorderpurrow/list?toPurId=" + toPurId, colunms);
			// table.setRootCodeValue(0);
			// table.setExpandColumn(2);
			// table.setIdField("toPurRowId");
			// table.setCodeField("toPurRowId");
			// table.setParentCodeField("");
			// table.setExpandAll(false);
			// table.init();
			// rowList.table = table;



		}
	}
});


// var rowList = {
// 	id: "rowTable",
// 	table: null,
// 	layerIndex: -1
// };
//
// /**
//  * 初始化表格的列
//  */
// rowList.initColumn = function () {
// 	var columns = [
// 		{field: 'selectItem', radio: true},
// 		{title: 'toPurRowId', field: 'toPurRowId', index: 'to_pur_row_id', width: 50, key: true, hidden: true},
// 		{title: '转储单日期', field: 'toDate', index: 'to_date', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '转储单号', field: 'toNo', index: 'to_no', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '转储单序号', field: 'toSeq', index: 'to_seq', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '仓库代码', field: 'warehouseCode', index: 'warehouse_code', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '物料代码', field: 'materialCode', index: 'material_code', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '供应商代码', field: 'supplierCode', index: 'supplier_code', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '指导单价', field: 'guidanceUnitPrice', index: 'guidance_unit_price', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '单价', field: 'unitPrice', index: 'unit_price', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '数量', field: 'qty', index: 'qty', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '总金额', field: 'totalAmount', index: 'total_amount', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '移动类型', field: 'moveTypeCode', index: 'move_type_code', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '所属工程师', field: 'engineer', index: 'engineer', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{
// 			title: '状态', field: 'dataStatus', width: 60, index: 'dataStatus', formatter: function (value, options, row) {
// 				return value === 0 ?
// 					'<span class="title title-danger">禁用</span>' :
// 					'<span class="title title-success">正常</span>';
// 			}
// 		},
// 		{title: '创建用户', field: 'creatorfield', index: 'creator_id', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '创建时间', field: 'createDate', index: 'create_date', align: 'center', valign: 'middle', sortable: true, width: '150px'},
// 		{title: '修改用户', field: 'modifierfield', index: 'modifier_id', align: 'center', valign: 'middle', sortable: true, width: '80px'},
// 		{title: '修改时间', field: 'modifyDate', index: 'modify_date', align: 'center', valign: 'middle', sortable: true, width: '150px'}]
// 	return columns;
// };
//
//
// function getRowId() {
// 	var selected = $('#rowTable').bootstrapTreeTable('getSelections');
// 	if (selected.length == 0) {
// 		alert("请选择一条记录");
// 		return false;
// 	} else {
// 		return selected[0].toPurRowId;
// 	}
// }


// $(function () {
// 	$.get(baseURL + "sys/dept/info", function (r) {
// 		var colunms = rowList.initColumn();
// 		var table = new TreeTable(rowList.id, baseURL + "sys/dept/list", colunms);
// 		table.setRootCodeValue(r.rowList);
// 		table.setExpandColumn(2);
// 		table.setIdField("toPurRowId");
// 		table.setCodeField("toPurRowId");
// 		table.setParentCodeField("");
// 		table.setExpandAll(false);
// 		table.init();
// 		rowList.table = table;
// 	});
// });


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
				// if (item.deptType === 1) {
				// 	return '<span class="label label-primary">集团</span>';
				// }
				if (item.deptType === 2) {
					return '<span class="label label-info">公司</span>';
				}
				// if (item.deptType === 3) {
				// 	return '<span class="label label-warning">部门</span>';
				// }
				if (item.deptType === 3) {
					return '<span class="label label-warning">门店</span>';
				}
				// if (item.deptType === 4) {
				// 	return '<span class="label label-warning">车间</span>';
				// }
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
