$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'wmstransferorder/list',
		datatype: "json",
		colModel: [
			{label: '移库单编号', name: 'toId', index: 'to_id', width: 120, key: true, hidden: true},
			{label: '移库单日期', name: 'toDate', index: 'to_date', width: 120},
			{label: '移库单号', name: 'toNo', index: 'to_no', width: 150},
			// {label: '来源仓库代码', name: 'sourWarehouseCode', index: 'sour_warehouse_code', width: 120},
			{label: '移出仓库', name: 'sourWarehouseName', index: 'sour_warehouse_code', width: 150},
			// {label: '目标仓库代码', name: 'destWarehouseCode', index: 'dest_warehouse_code', width: 120},
			{label: '移入仓库', name: 'destWarehouseName', index: 'dest_warehouse_code', width: 150},
			{
				label: '状态', name: 'dataStatus', width: 60, index: 'dataStatus', formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">作废</span>' :
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
		}
	});
});

var vm = new Vue({
	el: '#familyApp',
	data: {
		showList: true,
		showRow: false,
		showSn: false,
		title: null,
		addNew: false,
		wmsTransferOrder: {},
		wmsTransferOrderRow: {},
		wmsTransferOrderSn: {},
		qRow: {
			toNo: null
		},
		qSn: {
			toNo: null,
			toSeq: null,
		},
		defaultToDate: getCurrentDate(),
		defaultWarehouseCode: null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsTransferOrder = {toDate:vm.defaultToDate,sourWarehouseCode:vm.defaultWarehouseCode};
		},
		update: function (event) {
			var toId = getSelectedRow();
			if (toId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(toId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrder.toId == null ? "wmstransferorder/save" : "wmstransferorder/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrder),
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
			var toIds = getSelectedRows();
			if (toIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferorder/delete",
					contentType: "application/json",
					data: JSON.stringify(toIds),
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
		getInfo: function (toId) {
			$.get(baseURL + "wmstransferorder/info/" + toId, function (r) {
				vm.wmsTransferOrder = r.wmsTransferOrder;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		},
		getInfoForRow: function (toId) {
			$.get(baseURL + "wmstransferorder/info/" + toId, function (r) {
				vm.wmsTransferOrder = r.wmsTransferOrder;
				vm.resetRow();
				vm.reloadRow();
			});
		},
		showRowList: function () {
			var toId = getSelectedRow();
			if (toId == null) {
				return;
			}
			vm.showRow = true;
			vm.getInfoForRow(toId);
		},
		backToTransfer: function () {
			vm.showRow = false;

			vm.wmsTransferOrder = {};
			vm.reload();

			vm.qRow.toNo = "aaa";
			vm.reloadRow();
		},
		resetRow: function () {
			vm.wmsTransferOrderRow = {};
			vm.wmsTransferOrderRow.toDate = vm.wmsTransferOrder.toDate;
			vm.wmsTransferOrderRow.toNo = vm.wmsTransferOrder.toNo;
			vm.wmsTransferOrderRow.sourWarehouseName = vm.wmsTransferOrder.sourWarehouseName;
			vm.wmsTransferOrderRow.destWarehouseName = vm.wmsTransferOrder.destWarehouseName;
			vm.qRow.toNo = vm.wmsTransferOrder.toNo;
		},

//以下是行项信息
		reloadRow: function (event) {
			vm.showList = true;
			var page = $("#jqGridRow").jqGrid('getGridParam', 'page');
			$("#jqGridRow").jqGrid('setGridParam', {
				page: page,
				postData: {
					'toNo': vm.qRow.toNo
				},
			}).trigger("reloadGrid");
		},
		addRow: function () {
			vm.addNew = true;
			vm.resetRow();
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "新增移库单明细",
				area: ['700px', '500px'],
				shadeClose: false,
				content: jQuery("#addRowLayer")
			});
			$("#materialCodeRow").focus();
		},
		saveOrUpdateRow: function (event) {
			vm.wmsTransferOrderRow.toDate = vm.wmsTransferOrder.toDate;
			vm.wmsTransferOrderRow.toNo = vm.wmsTransferOrder.toNo;

			var url = vm.addNew ? "wmstransferorderrow/save" : "wmstransferorderrow/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrderRow),
				success: function (r) {
					if (r.code === 0) {
						alert('操作成功', function (index) {
							vm.reloadRow();
							vm.resetRow();
							$("#materialCodeRow").focus();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		delRow: function (event) {
			var grid = $("#jqGridRow");
			var rowKey = grid.getGridParam("selrow");
			if (!rowKey) {
				parent.layer.msg("请至少选择一条记录", {offset: 't', anim: 6});
				return;
			}
			var toSalRowIds = grid.getGridParam("selarrrow");

			if (toSalRowIds == null) {
				return;
			}

			confirm('确定要作废选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferorderrow/delete",
					contentType: "application/json",
					data: JSON.stringify(toSalRowIds),
					success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGridRow").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfoRowForSn: function (toRowId) {
			$.get(baseURL + "wmstransferorderrow/info/" + toRowId, function (r) {
				vm.wmsTransferOrderRow = r.wmsTransferOrderRow;
				vm.resetSn();
				vm.reloadSn();
			});
		},

		//串号相关操作
		backToRow: function () {
			vm.showSn = false;

			vm.wmsTransferOrderRow = {};
			vm.reloadRow();

			vm.qSn.toNo = "aaa";
			vm.reloadSn();
		},
		showSnList: function () {
			var grid = $("#jqGridRow");
			var rowKey = grid.getGridParam("selrow");
			if (!rowKey) {
				parent.layer.msg("请选择一条记录");
				return;
			}

			var selectedIDs = grid.getGridParam("selarrrow");
			if (selectedIDs.length > 1) {
				parent.layer.msg("只能选择一条记录");
				return;
			}

			var toRowId = selectedIDs[0];

			if (toRowId == null) {
				return;
			}
			vm.getInfoRowForSn(toRowId);

			// //打开串号管理界面
			vm.showSn = true;
			// var index = layer.open({
			// 	type: 2,
			// 	skin: 'layui-layer-molv',
			// 	title: "串号管理",
			// 	area: ['700px', '500px'],
			// 	shadeClose: false,
			// 	content: jQuery("#addSnLayer")
			// });
			//
			// layer.full(index);
			$("#materialSn").focus();
		},
		resetSn: function () {
			vm.wmsTransferOrderSn = {};
			vm.wmsTransferOrderSn.toDate = vm.wmsTransferOrderRow.toDate;
			vm.wmsTransferOrderSn.toNo = vm.wmsTransferOrderRow.toNo;
			vm.wmsTransferOrderSn.toSeq = vm.wmsTransferOrderRow.toSeq;
			vm.wmsTransferOrderSn.materialCode = vm.wmsTransferOrderRow.materialCode;

			vm.qSn.toNo = vm.wmsTransferOrderSn.toNo;
			vm.qSn.toSeq = vm.wmsTransferOrderSn.toSeq;
		},
		saveOrUpdateSn: function (event) {
			vm.wmsTransferOrderSn.toDate = vm.wmsTransferOrderRow.toDate;
			vm.wmsTransferOrderSn.toNo = vm.wmsTransferOrderRow.toNo;
			vm.wmsTransferOrderSn.toSeq = vm.wmsTransferOrderRow.toSeq;
			vm.wmsTransferOrderSn.materialCode = vm.wmsTransferOrderRow.materialCode;

			var url = vm.wmsTransferOrderSn.toSnId == null ? "wmstransferordersn/save" : "wmstransferordersn/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrderSn),
				success: function (r) {
					if (r.code === 0) {
						alert('操作成功', function (index) {
							vm.reloadSn();
							vm.resetSn();
							$("#materialSn").focus();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		delSn: function (event) {
			var grid = $("#jqGridSn");
			var rowKey = grid.getGridParam("selrow");
			if (!rowKey) {
				parent.layer.msg("请至少选择一条记录", {offset: 't', anim: 6});
				return;
			}
			var toSnIds = grid.getGridParam("selarrrow");

			if (toSnIds == null) {
				return;
			}

			confirm('确定要作废选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferordersn/delete",
					contentType: "application/json",
					data: JSON.stringify(toSnIds),
					success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGridSn").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		reloadSn: function (event) {
			vm.showList = true;
			var page = $("#jqGridSn").jqGrid('getGridParam', 'page');
			$("#jqGridSn").jqGrid('setGridParam', {
				page: page,
				postData: {
					'toNo': vm.qSn.toNo,
					'toSeq': vm.qSn.toSeq
				},
			}).trigger("reloadGrid");
		}
	}
});

$(function () {
	$("#jqGridRow").jqGrid({
		url: baseURL + 'wmstransferorderrow/list',
		datatype: "json",
		colModel: [
			{label: '移库单编号', name: 'toRowId', index: 'to_row_id', width: 80, key: true, hidden: true},
			{label: '移库单日期', name: 'toDate', index: 'to_date', width: 120},
			{label: '移库单号', name: 'toNo', index: 'to_no', width: 150},
			{label: '序号', name: 'toSeq', index: 'to_seq', width: 80},
			// {label: '来源工程师', name: 'sourEngineer', index: 'sour_engineer', width: 90},
			{label: '来源工程师', name: 'sourEngineerName', index: 'sour_engineer', width: 90},
			// {label: '目标工程师', name: 'destEngineer', index: 'dest_engineer', width: 90},
			{label: '目标工程师', name: 'destEngineerName', index: 'dest_engineer', width: 90},
			// {label: '商品代码', name: 'materialCode', index: 'material_code', width: 100},
			{label: '商品', name: 'materialDesc', index: 'material_code', width: 120},
			// {label: '供应商代码', name: 'supplierCode', index: 'supplier_code', width: 90},
			{label: '供应商', name: 'supplierName', index: 'supplier_code', width: 120},
			// {label: '指导单价', name: 'guidanceUnitPrice', index: 'guidance_unit_price', width: 80},
			{label: '单价', name: 'unitPrice', index: 'unit_price', width: 80},
			{label: '数量', name: 'qty', index: 'qty', width: 80},
			{label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80},
			{
				label: '状态', name: 'dataStatus', width: 60, index: 'dataStatus', formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">作废</span>' :
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
		pager: "#jqGridPagerRow",
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
		}
	});
});


/* resize jqGrid*/
$(function () {
	//jqGrid自适应高度
	$(window).on('resize', function () {
		var obj = $("#jqGridRow")
		if (obj.length > 0) {//存在表格
			var height = $(window).height() - obj.offset().top - $("#jqGridPagerRow").outerHeight() - 1;
			obj.setGridHeight(height > 60 ? height : 60);
			// msg("resize!" + obj.toString() + '\n' + $(window).height()+ '\n' + obj.offset().top+ '\n' +  $("#jqGridPager").outerHeight())
		}

	});
})


$(function () {
	$("#jqGridSn").jqGrid({
		url: baseURL + 'wmstransferordersn/list',
		datatype: "json",
		colModel: [
			{label: 'toSnId', name: 'toSnId', index: 'to_sn_id', width: 50, key: true, hidden: true},
			{label: '日期', name: 'toDate', index: 'to_date', width: 120},
			{label: '单据号', name: 'toNo', index: 'to_no', width: 150},
			{label: '序号', name: 'toSeq', index: 'to_seq', width: 80},
			{label: '商品', name: 'materialDesc', index: 'material_code', width: 150},
			{label: '串号', name: 'materialSn', index: 'material_sn', width: 200},
			{
				label: '状态', name: 'dataStatus', width: 60, index: 'dataStatus', formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">作废</span>' :
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
		pager: "#jqGridPagerSn",
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
		}
	});
});

/* resize jqGrid*/
$(function () {
	//jqGrid自适应高度
	$(window).on('resize', function () {
		var obj = $("#jqGridSn")
		if (obj.length > 0) {//存在表格
			var height = $(window).height() - obj.offset().top - $("#jqGridPagerSn").outerHeight() - 1;
			obj.setGridHeight(height > 60 ? height : 60);
			// msg("resize!" + obj.toString() + '\n' + $(window).height()+ '\n' + obj.offset().top+ '\n' +  $("#jqGridPager").outerHeight())
		}

	});
})

/* 当前日期 */
function getCurrentDate() {
	var date = new Date();
	var mon = date.getMonth() + 1;
	var day = date.getDate();
	var currentDate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
	// console.log(currentDate);
	return currentDate;
}
