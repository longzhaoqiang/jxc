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