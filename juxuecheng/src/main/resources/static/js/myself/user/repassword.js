$(function () {
    checkLogin();
    $("#tap").hide();
})


function commit() {
    checkLogin();

    // 前端校验
    var oldPassword = $("#oldpassword").val();
    var newPassword = $("#newPassword").val();
    var reNewPassword = $("#reNewPassword").val();
    if (oldPassword == ""){
        $("#tapContent").html("密码不能为空");
        $("#tap").show();
        return false;
    } else if (newPassword == ""){
        $("#tapContent").html("新密码不能为空");
        $("#tap").show();
        return false;
    } else if (reNewPassword == ""){
        $("#tapContent").html("确认新密码不能为空");
        $("#tap").show();
        return false;
    } else if (reNewPassword != newPassword){
        $("#tapContent").html("重复密码两次输入不一致");
        $("#tap").show();
        return false;
    }

    // 发送到后台
    var url = "/user/resetPw_1";
    $.ajax({
        url: url,
        type: "POST",
        data: {"oldPw":oldPassword,"newPs":newPassword,"reNewPw":reNewPassword},
        success: function (data) {
            var code = data.resultCode;
            var msg = data.resultMsg;
            if (code != "1"){
                $("#tapContent").html(msg);
                $("#tap").show();
                return false;
            } else {
                window.location.href = "/login";
            }
        }
    })
}

// 检查是否登录
function checkLogin() {
    var url = "/user/checkLogin";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == -1){
                window.location.href = "/login";
            }
        }
    })
}