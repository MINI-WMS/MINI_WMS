/**
 * Created by ltsznh on 2017/5/14.
 */
/* 初始化meterial */
function materialInit() {
    $.material.init();
    $("#dropdown-menu select").dropdown();
}

/* resize jqGrid*/
$(function () {
    //jqGrid自适应高度
    $(window).on('resize', function () {
        var obj = $("#jqGrid")
        if (obj.length > 0) {//存在表格
            var height = $(window).height() - obj.offset().top - $("#jqGridPager").outerHeight() - 1;
            obj.setGridHeight(height > 60 ? height : 60);
            // msg("resize!" + obj.toString() + '\n' + $(window).height()+ '\n' + obj.offset().top+ '\n' +  $("#jqGridPager").outerHeight())
        }

    });
})

