$(function () {
    var url = "/user/checkLogin";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == "-1"){
                $("#user_name").html("未登录");
            } else {
                var user_name = data['result']['userName'];
                $("#user_name").html(user_name);
            }
        }
    });

    check_register();
})

// 检查是否注册过
function check_register() {
    var url = "/buss/checkRegister";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            var msg = data.resultMsg;
            if (code == "1"){ // 0已注册 1未注册
                $("#buss_register").html(msg)
                $("#tobe-buss").css("pointer-events","none");
            }
        }
    })
}

// 被收藏
function besaved() {
    checkLogin();
}

// 浏览量
function readme() {
    var action = "/home";
    checkLogin(action);
}

// 我的收藏
function my_saved() {
    var action = "/home";
    checkLogin(action);
}

// 课程添加
function add_course() {
    var action = "/buss_add";
    checkLogin(action);
}

// 课程管理
function course_manager() {
    var action = "/course_add";
    checkLogin(action);
}

// 教师管理
function teacher_manager() {
    var action = "/teacher_add";
    checkRegister(action);
}

// 未上架
function no_use() {
    checkLogin();
}

// 招聘信息
function applyee() {
    checkLogin();
}

// 首页管理
function constomer_words() {
    var action = "/buss_add";
    checkLogin(action);
}

// 注册成为商家
function tobe_buss() {
    var action = "/buss_register";
    checkLogin(action);
}

// 优惠券
function youhui_quan() {
    checkLogin();
}

// 我的信息
function my_info() {
    var action = "/myInfo";
    checkLogin(action);
}

// 意见反馈
function idea() {
    checkLogin();
}

// 退出登录
function loginout() {
    var url = "/yingsu/user/loginout";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            window.location.href="/";
        }
    })
}

// 检查是否登录
function checkLogin(action) {
    var url = "/user/checkLogin";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == -1){
                window.location.href = "/login";
            } else {
                window.location.href = action;
            }
        }
    })
}

// 检查是否商家注册过
function checkRegister(action) {
    var url = "/buss/checkRegister";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == 0){
                alert("你尚未登录，请登录");
                window.location.href = "/login";
            } else if (code == -100){
                alert("你还未注册成商家");
                window.location.href = "/buss_register";
            } else {
                window.location.href = action;
            }
        }
    })
}