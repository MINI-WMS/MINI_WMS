$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'wmsmovetype/list',
        datatype: "json",
        colModel: [
			{ label: '移动类型代码', name: 'moveTypeCode', index: 'move_type_code', width: 100, key: true },
			{ label: '移动类型名称', name: 'moveTypeDesc', index: 'move_type_desc', width: 120 },
			{ label: '移动方向', name: 'moveDirection', index: 'move_direction', width: 80 },
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
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
		shrinkToFit: true,
        pager: "#jqGridPager",
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#familyApp',
	data:{
		showList: true,
		title: null,
		addNew: false,
		wmsMoveType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.wmsMoveType = {};
		},
		update: function (event) {
			var moveTypeCode = getSelectedRow();
			if(moveTypeCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(moveTypeCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "wmsmovetype/save" : "wmsmovetype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.wmsMoveType),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var moveTypeCodes = getSelectedRows();
			if(moveTypeCodes == null){
				return ;
			}

			confirm('确定要禁用选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "wmsmovetype/delete",
				    contentType: "application/json",
				    data: JSON.stringify(moveTypeCodes),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(moveTypeCode){
			$.get(baseURL + "wmsmovetype/info/"+moveTypeCode, function(r){
                vm.wmsMoveType = r.wmsMoveType;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		}
	}
});