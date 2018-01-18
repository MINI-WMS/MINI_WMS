

var vm = new Vue({
	el: '#familyApp',
	data: {
		showList: true,
		title: null,
		addNew: false,
		pubSn: {},
		q:{
			snDate:getCurrentDate()
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		// add: function () {
		// 	vm.showList = false;
		// 	vm.title = "新增";
		// 	vm.addNew = true;
		// 	vm.pubSn = {};
		// },
		// update: function (event) {
		// 	var snId = getSelectedRow();
		// 	if(snId == null){
		// 		return ;
		// 	}
		// 	vm.showList = false;
		//    vm.title = "修改";
		// 	vm.addNew = false;
		//
		//    vm.getInfo(snId)
		// },
		// saveOrUpdate: function (event) {
		// 	var url = vm.pubSn.snId == null ? "pubsn/save" : "pubsn/update";
		// 	$.ajax({
		// 		type: "POST",
		// 	    url: baseURL + url,
		// 	    contentType: "application/json",
		// 	    data: JSON.stringify(vm.pubSn),
		// 	    success: function(r){
		// 	    	if(r.code === 0){
		// 				alert('操作成功', function(index){
		// 					vm.reload();
		// 				});
		// 			}else{
		// 				alert(r.msg);
		// 			}
		// 		}
		// 	});
		// },
		// del: function (event) {
		// 	var snIds = getSelectedRows();
		// 	if (snIds == null) {
		// 		return;
		// 	}
		//
		// 	confirm('确定要删除选中的记录？', function () {
		// 		$.ajax({
		// 			type: "POST",
		// 			url: baseURL + "pubsn/delete",
		// 			contentType: "application/json",
		// 			data: JSON.stringify(snIds),
		// 			success: function (r) {
		// 				if (r.code == 0) {
		// 					alert('操作成功', function (index) {
		// 						$("#jqGrid").trigger("reloadGrid");
		// 					});
		// 				} else {
		// 					alert(r.msg);
		// 				}
		// 			}
		// 		});
		// 	});
		// },
		DayEndClearing: function (event) {// 日结
			var snIds = getSelectedRows();
			if (snIds == null) {
				return;
			}

			confirm('确定要对选中的记录日期的单据进行日结处理？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "pubsn/DayEndClearing",
					contentType: "application/json",
					data: JSON.stringify(snIds),
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
		DayEndClearing2: function (event) {// 反日结
			var snIds = getSelectedRows();
			if (snIds == null) {
				return;
			}

			confirm('确定要对选中的记录日期的单据进行反日结处理？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "pubsn/DayEndClearing2",
					contentType: "application/json",
					data: JSON.stringify(snIds),
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
		getInfo: function (snId) {
			$.get(baseURL + "pubsn/info/" + snId, function (r) {
				vm.pubSn = r.pubSn;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {
					'snDate': vm.q.snDate
				},
				page: page
			}).trigger("reloadGrid");
		}
	}
});


$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'pubsn/list',
		datatype: "json",
		colModel: [
			{label: '编号', name: 'snId', index: 'sn_id', width: 50, key: true},
			{label: '单据日期', name: 'snDate', index: 'sn_date', width: 80},
			// {label: '单据类型', name: 'snType', index: 'sn_type', width: 80},
			{
				label: '单据类型', name: 'snType', width: 60, index: 'is_enabled', formatter: function (value, options, row) {
					if (value === 'YK') return '<span>移库</span>'
					if (value === 'CG') return '<span>采购</span>'
					if (value === 'XS') return '<span>销售</span>'
					// if (value === 2) return '<span class="label label-danger">日结</span>';
					// if (value === 3) return '<span class="label label-success">工程师</span>';
					// if (value === 4) return '<span class="label label-success">营业员</span>';
				}
			},
			{label: '当前编号', name: 'snNum', index: 'sn_num', width: 80},
			{
				label: '状态', name: 'snStatus', width: 60, index: 'sn_status', formatter: function (value, options, row) {
					if (value === 1) return '<span class="label label-success">正常</span>'
					if (value === 2) return '<span class="label label-danger">日结</span>';
					// if (value === 3) return '<span class="label label-success">工程师</span>';
					// if (value === 4) return '<span class="label label-success">营业员</span>';
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


/* 当前日期 */
function getCurrentDate() {
	var date = new Date();
	var mon = date.getMonth() + 1;
	var day = date.getDate();
	var currentDate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
	// console.log(currentDate);
	return currentDate;
}

vm.query();
