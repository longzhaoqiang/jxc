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
    choose_pic();
})

function register_buss() {
    var bussName = $("#bussName").val().replace(/(^\s*)|(\s*$)/g, "");
    var phone = $("#phone").val().replace(/(^\s*)|(\s*$)/g, "");
    var bussType = $("#bussType").val().replace(/(^\s*)|(\s*$)/g, "");
    var wechatCode = $("#wechatCode").val().replace(/(^\s*)|(\s*$)/g, "");
    var address = $("#address").val().replace(/(^\s*)|(\s*$)/g, "");

    if(bussName == ""){
        alert("请输入机构名称");
        return false;
    }
    if(phone == ""){
        alert("请输入联系电话");
        return false;
    }
    if(bussType == ""){
        alert("请选择机构类型");
        return false;
    }
    if(address == ""){
        address("请输入详细地址");
        return false;
    }

    var files = $("#file1").get(0).files[0]; //获取file控件中的内容
    if (files == undefined){
        if(!confirm("还未上传二维码图片，是否继续")){
            return false;
        }
    }
    var formData = new FormData();
    formData.append("file", files);
    formData.append("bussName", bussName);
    formData.append("phone", phone);
    formData.append("bussType", bussType);
    formData.append("wechatCode", wechatCode);
    formData.append("address", address);

    $.ajax({
        url: "/buss/addBuss",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        mimeType:"multipart/form-data",
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

// 图片上传时显示缩略图
function choose_pic() {
    $("#file1").on("change", function () {
        var file = this.files[0];
        if (this.files && file) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $("#img1").attr("src", e.target.result);
            }
            reader.readAsDataURL(file);
        }
        $("#select_img").text("重新选择");
        $("#img1").show();
        $("#upload_logo").show();
    });
}