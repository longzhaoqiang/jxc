$(function () {
    // 查看是否编辑过商家首页
    var bussId = $("#bussId").val();
    if (bussId == null){
        bussId = 124;
    }
    $.ajax({
        url: "/buss/getBussInfo",
        data: {"bussId":bussId},
        type: "POST",
        success: function (data) {
            var code = data['resultCode'];
            var imgStr = "";
            if (code == "1"){
                $("#phone").html(data['result']['phone']);
                $("#address").html(data['result']['address']);
                $("#intraduce").html(data['result']['bussIntroduce']);
                $("#teacher_power").html(data['result']['tearchpower']);
            }
        }
    })
})