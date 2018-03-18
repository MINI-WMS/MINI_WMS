//获取客户信息
function getPurSupplierSelect2(item) {
    $.ajax({
        type: "POST",
        url: baseURL + 'pursupplier/select2',
        dataType: "json",
        success: function(result){
            if(result.code == 0){
                var materialList   =  result.page.list;
                for (var i=0;i<materialList.length;i++)
                {
                    materialList[i].id = materialList[i].supplierCode;
                    materialList[i].text = materialList[i].supplierCode + " - " + materialList[i].supplierName;

                }
                item.select2({
                    data: materialList,
                    placeholder:'请选择客户',
                    allowClear:true
                })
            }else{
                layer.alert(result.msg);
            }
        }
    });
}

//获取项目代码
function getPubMaterialSelect2(item) {
    $.ajax({
        type: "POST",
        url: baseURL + 'pubmaterial/list',//全部查询出来
        dataType: "json",
        success: function(result){
            if(result.code == 0){
                var materialList   =  result.page.list;
                for (var i=0;i<materialList.length;i++)
                {
                    materialList[i].id = materialList[i].materialCode;
                    materialList[i].text = materialList[i].materialCode + " - "+ materialList[i].brandName + " - " + materialList[i].materialDesc;
                }
                item.select2({
                    data: materialList,
                    placeholder:'请选择商品',
                    allowClear:true
                })
            }else{
                layer.alert(result.msg);
            }
        }
    });
}

//获取工程师
function getEngineerSelect2(item) {
    $.ajax({
        type: "POST",
        url: baseURL + "sys/user/getStaff?userType=3",//3工程师；4营业员
        dataType: "json",
        success: function(result){
            if(result.code == 0){
                var engineerList   =  result.page.list;
                for (var i=0;i<engineerList.length;i++)
                {
                    engineerList[i].id = engineerList[i].userId;
                    engineerList[i].text = engineerList[i].deptName + " - " + engineerList[i].username;
                }
                item.select2({
                    data: engineerList,
                    placeholder:'请选择工程师',
                    allowClear:true
                })
            }else{
                layer.alert(result.msg);
            }
        }
    });
}