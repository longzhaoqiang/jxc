$(function () {
    var openId = $("#openId").val();

    var url = "/buss/checkRegister";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            var bussId = data.resultMsg;
            if (code == "1"){ // 0已注册 1未注册
                $("#bussId").attr("value",bussId);
                $("#buss_register").html("您已注册成为商家")
                $("#tobe-buss").css("pointer-events","none");
            } else if (code == "0"){
                $("#buss_register").html("您还不是商家，立即注册")
            } else if(code == "-1") {
                // 进入home页时，如果数据库没有获取到微信用户信息
                window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2fbe7ca7c970259&redirect_uri=http://www.juxuecheng.com/user/login/&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            }
        }
    })

    get_wx_info(openId);
})

// 进入我的页面时检查是否注册过
function get_wx_info(openId) {
    var url = "/user/getWxInfo";
    $.ajax({
        type: "POST",
        url: url,
        data: {"openId":openId},
        success: function (data) {
            var code = data.resultCode;
            if (code == "0"){
                $("#user_name").html("未登录");
            } else {
                var nickName = data['result']['nickName'];
                var headImgUrl = data['result']['headImgUrl'];
                $("#user_name").html(nickName);
                $("#head_img").attr("src",headImgUrl)
            }
        }
    });
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
    var bussId = $("#bussId").val();
    var action = "/course_add?param1="+bussId;
    checkRegister(action);
}

// 课程管理
function course_manager() {
    var bussId = $("#bussId").val();
    var action = "/course?param1="+bussId;
    checkRegister(action);
}

// 教师管理
function teacher_manager() {
    var bussId = $("#bussId").val();
    var action = "/teacher_add?param1="+bussId;
    checkRegister(action);
}

// 未上架
function no_use() {
    checkLogin();
}

// 招聘信息
function recruit() {
    var action = "/recruit";
    checkLogin(action);
}

// 首页管理
function constomer_words() {
    var bussId = $("#bussId").val();
    var action = "/buss_add?param1="+bussId;
    checkRegister(action);
}

// 注册成为商家
function tobe_buss() {
    window.location.href = "/buss_register";
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

// 检查是否注册成商家
function checkRegister(action) {
    var url = "/buss/checkRegister";
    $.ajax({
        type: "POST",
        url: url,
        success: function (data) {
            var code = data.resultCode;
            if (code == 0){
                if(confirm("注册成商家即可操作")){
                    window.location.href = "/buss_register";
                }
            } else {
                window.location.href = action;
            }
        }
    })
}