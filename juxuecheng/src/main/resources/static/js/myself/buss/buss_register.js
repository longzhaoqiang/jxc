$(function () {
    $.ajax({
        url: "/buss/getTypeList",
        type: "POST",
        success: function (data) {
            var result = data['result'];
            var typeStr = "";
            for (var i = 0;i<result.length;i++){
                typeStr += "<option>"+data['result'][i]['classType'];+"</option>>"
            }
            $("#bussType").html(typeStr);
        }
    })
})

function register_buss() {
    $.ajax({
        url: "/buss/addBuss",
        type: "POST",
        data: $("#bussForm").serialize(),
        success: function (data) {
            var code = data['resultCode'];
            if (code == "1"){
                window.location.href = "/home";
            } else {
                alert("网络异常请稍后再试");
            }
        }
    })
}