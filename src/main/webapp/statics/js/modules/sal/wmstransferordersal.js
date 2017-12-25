$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'wmstransferordersal/list',
		datatype: "json",
		colModel: [
			{label: 'toSalId', name: 'toSalId', index: 'to_sal_id', width: 50, key: true, hidden: true},
			{label: '销售日期', name: 'toDate', index: 'to_date', width: 120},
			{label: '销售单号', name: 'toNo', index: 'to_no', width: 150},
			{label: '仓库代码', name: 'warehouseCode', index: 'warehouse_code', width: 120},
			{label: '仓库', name: 'warehouseName', index: 'warehouse_code', width: 150},
			{label: '客户代码', name: 'customerCode', index: 'customer_code', width: 120},
			{label: '客户', name: 'customerName', index: 'customer_code', width: 150},
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
		title: null,
		addNew: false,
		wmsTransferOrderSal: {},
		wmsTransferOrderSalRow: {},
		qRow: {
			toNo: null
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
			vm.wmsTransferOrderSal = {};
		},
		update: function (event) {
			var toSalId = getSelectedRow();
			if (toSalId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(toSalId)
		},
		saveOrUpdate: function (event) {
			var url = vm.wmsTransferOrderSal.toSalId == null ? "wmstransferordersal/save" : "wmstransferordersal/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrderSal),
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
			var toSalIds = getSelectedRows();
			if (toSalIds == null) {
				return;
			}
			confirm('确定要作废选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferordersal/delete",
					contentType: "application/json",
					data: JSON.stringify(toSalIds),
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
		getInfo: function (toSalId) {
			$.get(baseURL + "wmstransferordersal/info/" + toSalId, function (r) {
				vm.wmsTransferOrderSal = r.wmsTransferOrderSal;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				page: page
			}).trigger("reloadGrid");
		},
		getInfoForRow: function (toSalId) {
			$.get(baseURL + "wmstransferordersal/info/" + toSalId, function (r) {
				vm.wmsTransferOrderSal = r.wmsTransferOrderSal;
				vm.resetRow();
				vm.reloadRow();
			});
		},
		showRowList: function () {
			var toSalId = getSelectedRow();
			if (toSalId == null) {
				return;
			}
			vm.showRow = true;
			vm.getInfoForRow(toSalId);
		},
		backToSal: function () {
			vm.showRow = false;

			vm.wmsTransferOrderSal = {};
			vm.reload();

			vm.qRow.toNo="aaa";
			vm.reloadRow();
		},
		resetRow: function () {
			vm.wmsTransferOrderSalRow = {};
			vm.wmsTransferOrderSalRow.toDate = vm.wmsTransferOrderSal.toDate;
			vm.wmsTransferOrderSalRow.toNo = vm.wmsTransferOrderSal.toNo;
			vm.wmsTransferOrderSalRow.warehouseName = vm.wmsTransferOrderSal.warehouseName;
			vm.wmsTransferOrderSalRow.customerName = vm.wmsTransferOrderSal.customerName;
			vm.qRow.toNo = vm.wmsTransferOrderSal.toNo;
		},

//以下是行项信息
		reloadRow: function (event) {
			vm.showList = true;
			var page = $("#jqGridRow").jqGrid('getGridParam','page');
			$("#jqGridRow").jqGrid('setGridParam',{
				page:page,
				postData: {
					'toNo': vm.qRow.toNo
				},
			}).trigger("reloadGrid");
		},
		addRow: function(){
			vm.addNew = true;
			vm.resetRow();
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "新增销售单明细",
				area: ['700px', '400px'],
				shadeClose: false,
				content: jQuery("#addRowLayer")
			});
			$("#materialCodeRow").focus();
		},
		saveOrUpdateRow: function (event) {
			var url = vm.addNew ? "wmstransferordersalrow/save" : "wmstransferordersalrow/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.wmsTransferOrderSalRow),
				success: function(r){
					if(r.code === 0){
						alert('操作成功', function(index){
							vm.reloadRow();
							vm.resetRow();
							$("#materialCodeRow").focus();
						});
					}else{
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

			confirm('确定要作废选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: baseURL + "wmstransferordersalrow/delete",
					contentType: "application/json",
					data: JSON.stringify(toSalRowIds),
					success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGridRow").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});

$(function () {
	$("#jqGridRow").jqGrid({
		url: baseURL + 'wmstransferordersalrow/list',
		datatype: "json",
		colModel: [
			{ label: 'toSalRowId', name: 'toSalRowId', index: 'to_sal_row_id', width: 50, key: true,hidden:true},
			{ label: '销售日期', name: 'toDate', index: 'to_date', width: 120 },
			{ label: '销售单号', name: 'toNo', index: 'to_no', width: 150 },
			{ label: '序号', name: 'toSeq', index: 'to_seq', width: 80 },
			{ label: '商品代码', name: 'materialCode', index: 'material_code', width: 120 },
			{ label: '商品', name: 'materialName', index: 'material_code', width: 150 },
			{ label: '指导单价', name: 'guidanceUnitPrice', index: 'guidance_unit_price', width: 80 },
			{ label: '单价', name: 'unitPrice', index: 'unit_price', width: 80 },
			{ label: '数量', name: 'qty', index: 'qty', width: 80 },
			{ label: '总金额', name: 'totalAmount', index: 'total_amount', width: 80 },
			{label: '工程师编号', name: 'engineer', index: 'engineer', width: 90},
			{label: '工程师', name: 'engineerName', index: 'engineer', width: 90},
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
		rowList : [10,30,50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth:true,
		multiselect: true,
		shrinkToFit: false,
		pager: "#jqGridPagerRow",
		jsonReader : {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames : {
			page:"page",
			rows:"limit",
			order: "order"
		},
		gridComplete:function(){
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