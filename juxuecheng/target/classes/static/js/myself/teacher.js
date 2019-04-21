$(function () {
    // 图片上传时显示缩略图
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
})


function teacher_add() {
    var teacher_name = $("#teacher_name").val();
    var teacher_info = $("#teacher_info").val();
    if (teacher_name == ""){
        alert("请输入教师姓名");
        return false;
    }
    var files = $("#file1").get(0).files[0]; //获取file控件中的内容
    if (files == undefined){
        alert("你未上传图片，将使用默认图片");
    }
    var formData = new FormData();
    formData.append("file", files);
    formData.append("teacher_name", teacher_name);
    formData.append("teacher_info", teacher_info);
    $.ajax({
        url: "/teacher/add",
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        dataType: "json",
        mimeType:"multipart/form-data",
        success: function (data) {
            var msg = data.resultMsg;
            if (data.resultCode == 1) {
                window.location.reload();
            } else {
                alert(msg);
            }
        }
    });
}