$(function () {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId},
        success: function (data) {
            var code = data.resultCode;
            var result = data.result;
            var course_str = '';
            if (code == "1") {
                for (var i = 0; i < result.length; i++) {
                    var courseName = data['result'][i]['courseName'];
                    if (courseName.length > 7){
                        courseName = courseName.substring(0,8) + "...";
                    }
                    course_str += "<div class='course-1'><div style='width:90%;border-radius: 5px;border: 1px solid #aba9a9'><a id=" + result[i]['id'] + " onclick='javascript:course_info(this)'>" +
                        "                <div style='height: 110px'>" +
                        "                    <img src='/img/myself/course_bg.jpg' style='width: 100%;border-radius: 2px;'>" +
                        "                </div></a>" +
                        "                <div class='course-n-1'>" +
                        "                <span style='margin-left: 10px;'>" + courseName + "</span></div>" +
                        "                <div><div style='margin-left: 10px;'>" +
                        "                   <div><div class='wl-68'>" +
                        "                   <span class='course-fee'>¥"+result[i]['courseFee']+"</span></div>"+
                        "                    <div><span class='c-blue glyphicon glyphicon-pencil' id=" + result[i]['id'] + " onclick='update_course(this)'></span>" +
                        "                    <span class='course-color glyphicon glyphicon-trash' style='margin-left: 10px' id=" + result[i]['id'] + " onclick='delete_course(this)'></span>" +
                        "                </div></div></div></div>" +
                        "<hr class='hr-1' width='90%'/></div></div>";
                }
                $("#courseList").html(course_str);
            } else {
                alert("服务器繁忙！");
            }
        }
    })

    $("#all_course").css("background-color","#FF5D0C");
    $("#all_course").css("color","#FDFDFF");
})

function all_course() {
    $("#all_course").css("color","#FDFDFF");
    $("#good_course").css("color","#FF5D0C");
    $("#try_course").css("color","#FF5D0C");
    $("#all_course").css("background-color","#FF5D0C");
    $("#good_course").css("background-color","#FDFDFF");
    $("#try_course").css("background-color","#FDFDFF");
}

function good_course() {
    $("#good_course").css("color","#FDFDFF");
    $("#all_course").css("color","#FF5D0C");
    $("#try_course").css("color","#FF5D0C");
    $("#good_course").css("background-color","#FF5D0C");
    $("#all_course").css("background-color","#FDFDFF");
    $("#try_course").css("background-color","#FDFDFF");
}

function try_course() {
    $("#try_course").css("color","#FDFDFF");
    $("#all_course").css("color","#FF5D0C");
    $("#good_course").css("color","#FF5D0C");
    $("#try_course").css("background-color","#FF5D0C");
    $("#all_course").css("background-color","#FDFDFF");
    $("#good_course").css("background-color","#FDFDFF");
}