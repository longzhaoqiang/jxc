$(function () {
    checkLogin();
    // 页面加载时获取
    getTypeList();
})

// 获取类型列表
function getTypeList() {
    var url = "/buss/getTypeList";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            for(var i=0;i<data.result.length;i++){
                $("#bussType").append("<option value='"+data.result[i]['id']+"'>"+data.result[i]['classType']+"</option>");
            }
        }
    })
}

// 商家注册
function registerBuss() {
    var url = "/buss/addBuss";
    $.ajax({
        type: "POST",
        url: url,
        data: $('#bussForm').serialize(),
        success: function (data) {
            var msg = data.resultMsg;
            alert(msg);
            window.location.href = "/home";
        }
    })
}

// 检查是否登录
function checkLogin() {
    var url = "/user/checkLogin";
    var resultCode = "";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == -1){
                window.location.href = "/login";
            }
        },
        error: function (e) {
            alert(e);
        }
    })
}