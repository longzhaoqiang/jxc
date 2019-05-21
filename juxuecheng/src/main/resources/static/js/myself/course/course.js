$(function () {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId},
        success: function (data) {
            var code = data.resultCode;
            var msg = data.resultMsg;
            var result = data.result;
            var course_str = '';
            if (code == "1") {
                for (var i = 0; i < result.length; i++) {
                    course_str += "<div class='course-1'><a id=" + result[i]['id'] + " onclick='javascript:course_info(this)'>" +
                        "                <div style='height: 110px'>" +
                        "                    <img src='/img/myself/course_bg.jpg' style='width: 90%;border-radius: 5px;'>" +
                        "                    <div class='course-2'>" +
                        "                        <p class='course-color'>" + data['result'][i]['courseName'] + "</p>" +
                        "                    </div>" +
                        "                </div></a>" +
                        "                <div class='course-name' style='width: 90%'>" +
                        "                <span>" + data['result'][i]['courseName'] + "</span></div>" +
                        "                <div style='width: 90%'>" +
                        "                    <span class='c-blue glyphicon glyphicon-pencil' id=" + result[i]['id'] + " onclick='update_course(this)'></span>" +
                        "                    <span class='course-color glyphicon glyphicon-trash' style='margin-left: 10px' id=" + result[i]['id'] + " onclick='delete_course(this)'></span>" +
                        "                </div>" +
                        "<hr style='border-top: 3px solid coral;' width='90%' color='#6f5499'/>" +
                        "            </div>";
                }
                $("#courseList").html(course_str);
            } else {
                alert("网络异常！");
            }
        }
    })
})

function course_add() {
    window.location.href = "/course_add";
}

// 查询课程详情
function course_info(obj) {
    var course_id = obj.id;
    window.location.href = "/course/getCourse?param1=" + course_id;
}

function update_course(obj) {
    var course_id = obj.id;
    window.location.href = "/course/getCourse?param1="+course_id+"&flag=1";
}

function delete_course(obj) {
    var id = obj.id;
    var is_delete = confirm("是否删除?");
    if (is_delete) {
        var url = "/course/delete";
        $.ajax({
            url: url,
            type: "POST",
            data: {"id" : id},
            success: function (data) {
                var code = data.resultCode;
                if (code == "1"){
                    window.location.reload(this);
                } else {
                    alert("网络异常！请稍后再试");
                }
            }
        })
    }
}