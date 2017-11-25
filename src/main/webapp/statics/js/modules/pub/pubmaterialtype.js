$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pubmaterialtype/list',
        datatype: "json",
        colModel: [
			{ label: '物料类型代码', name: 'materialTypeCode', index: 'material_type_code', width: 50, key: true },
			{ label: '物料类型名称', name: 'materialTypeDesc', index: 'material_type_desc', width: 80 },
			{
				label: '状态', name: 'isEnabled', width: 60, index: 'is_enabled', formatter: function (value, options, row) {
				return value === 0 ?
					'<span class="label label-danger">禁用</span>' :
					'<span class="label label-success">正常</span>';
			}
			},
			{ label: '创建用户', name: 'creatorName', index: 'creator_id', width: 80 },
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }, 
			{ label: '修改用户', name: 'modifierName', index: 'modifier_id', width: 80 },
			{ label: '修改时间', name: 'modifyDate', index: 'modify_date', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
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
		q:{
			materialTypeDesc:null,
			isEnabled:1
		},
		showList: true,
		title: null,
		addNew:false,
		pubMaterialType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.addNew = true;
			vm.pubMaterialType = {};
		},
		update: function (event) {
			var materialTypeCode = getSelectedRow();
			if(materialTypeCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			vm.addNew = false;

            vm.getInfo(materialTypeCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.addNew ? "pubmaterialtype/save" : "pubmaterialtype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.pubMaterialType),
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
			var materialTypeCodes = getSelectedRows();
			if(materialTypeCodes == null){
				return ;
			}

			confirm('确定要禁用选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "pubmaterialtype/delete",
				    contentType: "application/json",
				    data: JSON.stringify(materialTypeCodes),
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
		getInfo: function(materialTypeCode){
			$.get(baseURL + "pubmaterialtype/info/"+materialTypeCode, function(r){
                vm.pubMaterialType = r.pubMaterialType;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData: {'materialTypeDesc': vm.q.materialTypeDesc, 'isEnabled': vm.q.isEnabled},
                page:page
            }).trigger("reloadGrid");
		}
	}
});