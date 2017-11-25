$(function () {
	$("#jqGrid").jqGrid({
		url: baseURL + 'pubarea/list',
		datatype: "json",
		colModel: [
			{label: '上级地区代码', name: 'parentAreaCode', index: 'parent_area_code', width: 80},
			{label: '上级地区代码', name: 'parentAreaName', index: 'parent_area_code', width: 80},
			{label: '地区代码', name: 'areaCode', index: 'area_code', width: 80, key: true},
			{label: '地区名称', name: 'areaName', index: 'area_name', width: 80},
			{
				label: '地区类型', name: 'areaType', width: 60,index: 'area_type', formatter: function (value, options, row) {
				if (value === 1) {
					return '<span class="label label-primary">省(直辖市)</span>';
				}
				if (value === 2) {
					return '<span class="label label-info">市</span>';
				}
				if (value === 3) {
					return '<span class="label label-warning">县(市辖区)</span>';
				}
				if (value === 4) {
					return '<span class="label label-warning">乡镇</span>';
				}
			}
			},
			{label: '创建用户', name: 'creatorName', index: 'creator_id', width: 80},
			{label: '创建时间', name: 'createDate', index: 'create_date', width: 80},
			{label: '修改用户', name: 'modifierName', index: 'modifier_id', width: 80},
			{label: '修改时间', name: 'modifyDate', index: 'modify_date', width: 80}
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

var vm = new Vue({
	el: '#familyApp',
	data: {
		q:{
			areaName:null,
			areaType:null
		},
		showList: true,
		title: null,
		addNew: false,
		pubArea: {
			areaType:1
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
			vm.pubArea = {};
		},
		update: function (event) {
			var areaCode = getSelectedRow();
			if (areaCode == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";
			vm.addNew = false;

			vm.getInfo(areaCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "pubarea/save" : "pubarea/update";
			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.pubArea),
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
			var areaCodes = getSelectedRows();
			if (areaCodes == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
					url: baseURL + "pubarea/delete",
					contentType: "application/json",
					data: JSON.stringify(areaCodes),
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
		getInfo: function (areaCode) {
			$.get(baseURL + "pubarea/info/" + areaCode, function (r) {
				vm.pubArea = r.pubArea;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {'areaName': vm.q.areaName, 'areaType': vm.q.areaType},
				page: page
			}).trigger("reloadGrid");
		}
	}
});