//获取客户信息
function getPurSupplierSelect2(item) {
    $.ajax({
        type: "POST",
        url: baseURL + 'pursupplier/select2',
        dataType: "json",
        success: function(result){
            if(result.code == 0){
                var supplierList   =  result.page.list;
                for (var i=0;i<supplierList.length;i++)
                {
                    supplierList[i].id = supplierList[i].supplierCode;
                    supplierList[i].text = supplierList[i].supplierCode + " - " + supplierList[i].supplierName;

                }
                item.select2({
                    data: supplierList,
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
                var supplierList   =  result.page.list;
                for (var i=0;i<supplierList.length;i++)
                {
                    supplierList[i].id = supplierList[i].materialCode;
                    supplierList[i].text = supplierList[i].materialCode + " - "+ supplierList[i].brandName + " - " + supplierList[i].materialDesc;

                }
                item.select2({
                    data: supplierList,
                    placeholder:'请选择商品',
                    allowClear:true
                })
            }else{
                layer.alert(result.msg);
            }
        }
    });
}