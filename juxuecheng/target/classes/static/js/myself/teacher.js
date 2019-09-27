$(function () {
    var bussId = $("#bussId").val();
    get_teachers(bussId);
    choose_pic();
})

// 进入页面时加载教师列表
function get_teachers(bussId) {
    $("#no_teacher").hide();
    var url = "/teacher/getList?bussId="+bussId;
    $.ajax({
        url: url,
        type: "POST",
        success: function (data) {
            var code = data.resultCode;
            var result = data.result;
            var teacherStr = "";
            if (code == "1"){
                $("#null_data").hide();
                for (var i = 0;i<result.length;i++) {
                    var max_width = 30;
                    var teacher_introduce = data.result[i]['teacherIntroduce'];
                    var intro_length = teacher_introduce.length;
                    if (intro_length > max_width){
                        teacher_introduce = teacher_introduce.substring(0,max_width);
                        teacher_introduce = teacher_introduce + "......";
                    }
                    teacherStr += "<div><a id="+data.result[i]['id']+" onclick='javascript:teacher_info(this)'>\n" +
                        "                <div style='width: 24%;height: 80px;float: left;'>\n" +
                        "                    <img id=\"teacherImg\" class='img-border tea-img' src='https://yingsu-jxc.oss-cn-shanghai.aliyuncs.com/teacher_img/"+data.result[i]['teacherLogo']+"' style='width: 70px;'/>\n" +
                        "                </div>\n" +
                        "                <div style='width: 70%;float: left;line-height: 1.5;'>\n" +
                        "                    <div><span class='font-size-16-1 color-1' id="+data.result[i]['id']+">" + data.result[i]['teacherName']+"</span></div>\n" +
                    "                    <div class='intraduce-1 w-90'>\n" +
                    "                        <span class=\"font-color-1\" id=\"teacherIntroduce\">"+teacher_introduce+"</span>\n" +
                    "                    </div>\n" +
                    "                </div></a>\n" +
                    "                <hr style=\"filter: alpha(opacity=100,finishopacity=0,style=3);border-top: 3px solid #eee;\" width=\"100%\" color=\"#6f5499\"/>\n" +
                    "            </div>";
                }
            } else if (code == "-100"){
                alert("登录过期，请重新登录");
                window.location.href = "/login";
            } else if (code == "0"){
                $("#null_data").show();
                $("#add_mark").hide();
            }
            $("#teacher_list").html(teacherStr);
        }
    })
}

// 点击教师时显示详情
function teacher_info(obj) {
    $("#teacher_msg").css("display","block");
    $("#fade1").css("display","block");
    $("#fade1").attr("class","black_overlay")
    var teacher_id = obj.id;
    var url = "/teacher/getTeacher";
    $.ajax({
        url: url,
        type: "POST",
        data: {"teacherId": teacher_id},
        success: function (data) {
            $("#teacher_info_img").attr('src', 'https://yingsu-jxc.oss-cn-shanghai.aliyuncs.com/teacher_img/' + data.result['teacherLogo']);
            $("#teacher_info_name").html(data.result['teacherName']);
            $("#teacher_info_intro").html(data.result['teacherIntroduce'])
            $("#teacher_id").val(teacher_id);
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

// 添加教师
function teacher_add() {
    var bussId = $("#bussId").val();
    var teacher_name = $("#teacher_name").val();
    var teacher_introduce = $("#teacher_introduce").val();
    var teach_date = $("#teach_date").val();
    var teacher_subject = $("#teacher_subject").val();
    if (teacher_name == ""){
        alert("请输入教师姓名");
        return false;
    }
    if (teach_date == ""){
        alert("请输入教龄");
        return false;
    }
    if (teacher_subject == ""){
        alert("请输入所教学科");
        return false;
    }
    if (teacher_introduce == ""){
        alert("请输入教师介绍");
        return false;
    }
    var files = $("#file1").get(0).files[0]; //获取file控件中的内容
    if (files == undefined){
        alert("你未上传图片，将使用默认图片");
    }
    var formData = new FormData();
    formData.append("file", files);
    formData.append("teacher_name", teacher_name);
    formData.append("teacher_introduce", teacher_introduce);
    formData.append("teach_date", teach_date);
    formData.append("teacher_subject", teacher_subject);
    $.ajax({
        url: "/teacher/add?bussId="+bussId,
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

// 删除教师
function delete_teacher() {
    var is_delete = confirm("是否删除?");
    if (is_delete){
        var teacher_id = $("#teacher_id").val();
        var url = "/teacher/delete";
        $.ajax({
            url: url,
            type: "POST",
            data: {"id":teacher_id},
            success: function (data) {
                var code = data.resultCode;
                var msg = data.resultMsg;
                if (code != "1"){
                    alert(msg);
                } else {
                    window.location.reload();
                }
            }
        })
    } else {
        return false;
    }
}