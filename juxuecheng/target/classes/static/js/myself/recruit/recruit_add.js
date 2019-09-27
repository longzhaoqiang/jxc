$(function () {
    checkLogin();
})

function commit() {
    var tile = $("#recruitTitle").val();
    var experence = $("#experence").val();
    var education = $("#education").val();
    var recruitSalary = $("#recruitSalary").val();
    var postInfo = $("#postInfo").val();
    var data = $("#recruitForm").serialize();
    if (tile == ""){
        alert("请填写职位名称");
        return false;
    }
    if (experence == ""){
        alert("请填写任职经验");
        return false;
    }
    if (education == ""){
        alert("请填写学历要求");
        return false;
    }
    if (recruitSalary == ""){
        alert("请填写薪资待遇");
        return false;
    }
    if (postInfo == ""){
        alert("请填写职位详细信息");
        return false;
    }

    $.ajax({
        url: "/recruit/add",
        type: "POST",
        data: data,
        success: function (data) {
            var code = data.resultCode;
            if (code == "1"){

            } else {
                alert("网络异常！添加失败！");
            }
        },
    });
}

function checkLogin() {
    $.ajax({
        url: "/user/checkLogin",
        type: "POST",
        success: function (data) {
            var code = data.resultCode;
            if (code == "1"){

            } else {
                alert("请登录");
                window.location.href = "/login";
            }
        },
    });
}