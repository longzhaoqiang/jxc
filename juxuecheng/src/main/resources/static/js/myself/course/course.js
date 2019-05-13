$(function () {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId":bussId},
        success: function (data) {
            var code = data.resultCode;
            var msg = data.resultMsg;
            var result = data.result;
            var course_str = '';
            if (code == "1"){
                for (var i = 0;i < result.length;i++) {
                    course_str += "<div class='course-1'>" +
                        "                <div style='height: 110px'>" +
                        "                    <img src='/img/myself/course_bg.jpg' style='width: 90%'>" +
                        "                    <div class='course-2'>" +
                        "                        <p>"+data['result'][i]['courseName']+"</p>" +
                        "                    </div>" +
                        "                </div>" +
                        "                <div style='width: 90%'>" +
                        "                    <span class='course-3'>" + data['result'][i]['courseName'] + "</span>" +
                        "                </div>" +
                        "            </div>";
                }
                $("#courseList").html(course_str);
            } else {
                alert(msg);
            }
        }
    })
})

function course_add() {
    window.location.href = "/course_add";
}