$(function () {

})

function commit() {
    var userName = $("#userName").val().trim();
    var password = $("#password").val().trim();
    if (userName == ""){
        alert("用户名不能为空！")
        return false;
    } else if (password == ""){
        alert("密码不能为空！");
        return false;
    }

    $.ajax({
        url: "/user/login",
        type: "POST",
        data: {"userName":userName,"password":password},
        success: function (data) {
            var result = data;
            var resultCode = result.resultCode;
            var resultMsg = data.resultMsg;
            if (resultCode == "1"){
                window.location.href="/home";
            } else {
                alert(resultMsg);
            }
        }
    })
}

// 用户注册
function registerUser() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();

    if (userName == ""){
        alert("用户名不能为空");
        return false;
    } else if (password == ""){
        alert("密码不能为空");
        return false;
    } else if (password != repassword){
        alert("两次密码不一致");
        return false;
    }

    $.ajax({
        url: "/user/register",
        type: "POST",
        async: false,
        data: {"userName":userName,"password":password},
        success: function (data) {
            var result = data;
            var resultMsg = result.resultMsg;
            var resultCode = result.resultCode;
            if (resultCode == "1"){
                window.location.href="/login";
            } else if (resultCode == "2"){
                alert(resultMsg);
                window.location.href="/login";
            }
        }
    })
}