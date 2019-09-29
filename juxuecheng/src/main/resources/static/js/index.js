// 轮播
$(function () {
    // 顶部轮播图
    var mySwiper = new Swiper('.swiper-container', {
        // 如果需要分页器
        autoplay: true,
        pagination: {
            el: '.swiper-pagination'
        }
    });
    // 秒杀商品滑动
    var swiper = new Swiper('.seckill-wares', {
        slidesPerView: 3.5,
        spaceBetween: 5,
        freeMode: true
    });
    // 新闻资讯
    var swiper2 = new Swiper('.infoBox', {
        autoplay: true,
        delay: 5000,
        direction: 'vertical'
    });
    var bussId = $("#bussId").val();
    init(bussId);
    get_word(bussId);

    $("#buss_index").click(function () {
        window.location.href = "/buss_index?bussId=" + bussId;
    });

    // 微信分享功能
    wechatShare(bussId);

    // 加载精品课程
    good_course();
    // 加载免费试听课程
    try_course();
    // 加载所有课程
    all_course();
})

function home_openid() {
    // window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2fbe7ca7c970259&redirect_uri=http://www.juxuecheng.com/user/home/&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    window.location.href = "/home";
}

function enroll_online() {
    var bussId = $("#bussId").val();
    window.location.href = "/enroll_online?param1=" + bussId;
}

function course() {
    var bussId = $("#bussId").val();
    window.location.href = "/courseCoustomer?param1=" + bussId;
}

function recruit() {
    var bussId = $("#bussId").val();
    window.location.href = "/recruit?param1=" + bussId;
}

function contact_us() {
    var bussId = $("#bussId").val();
    window.location.href = "/contact_us?param1=" + bussId;
}

function init(bussId) {
    var url = "/index/main";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId},
        success: function (data) {
            var teacherModel = data.teacherModel;
            var teacherModelStr = "";
            for (var i = 0; i < teacherModel.length; i++) {
                teacherModelStr += "<div class='swiper-slide seckill-ware' style='width: 100px;'>" +
                    "                        <a id="+teacherModel[i]['id']+" onclick='teacher_info(this)'>" +
                    "                            <div><img style='height: 80px;width: 75px' src='https://yingsu-jxc.oss-cn-shanghai.aliyuncs.com/teacher_img/" + teacherModel[i]["teacherLogo"] + "'/></div>" +
                    "                            <p style='font-size: 14px'>" + teacherModel[i]['teacherName'] + "</p>" +
                    "                            <p style='font-size: 14px;font-weight: 700;color: #ff5500'>" + teacherModel[i]['teachDate'] + "年教龄</p>" +
                    "                            <p class='o-1' style='' font-weight: inherit;font-size: 14px;'>" + teacherModel[i]['teacherSubject'] + "</p>" +
                    "                        </a>\n" +
                    "                    </div>";
            }
            $("#teacherList").html(teacherModelStr);
        }
    })
}

// 点击教师时显示详情
function teacher_info(obj) {
    var top = ($(window).height() - $("#teacher_msg").height())/4;
    var left = ($(window).width() - $("#teacher_msg").width())/3;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $("#teacher_msg").css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } ).show();
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
            $("#teacher_info_img").attr('src', 'http://image.yingsuit.com/TeacherImg/' + data.result['teacherLogo']);
            $("#teacher_info_name").html(data.result['teacherName']);
            $("#teacher_info_intro").html(data.result['teacherIntroduce'])
            $("#teacher_id").val(teacher_id);
        },
        error: function (data) {
            alert(data);
        }
    })
}

// 关键词
function get_word(bussId) {
    var url = "/advice/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId},
        success: function (data) {
            var result = data.result;
            for (var i = 0; i <= result.length; i++) {
                $("#word_"+i).html(result[i]['word']);
            }
        }
    })
}

// 精品课程
function good_course() {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId, "type": 0},
        success: function (data) {
            var code = data.resultCode;
            var result = data.result;
            var str = "";
            if (code == "1") {
                for (var i = 0; i < result.length; i++) {
                    str += "<div class='swiper-slide seckill-ware' style='width: 103.571px;margin-right: 5px;'>" +
                        "                        <a id=" + result[i]['id'] + " onclick='course_info(this)'>" +
                        "                            <img src='/img/course-1.jpg'>" +
                        "                            <p class='red-color'>￥<strong>"+data['result'][i]['courseFee']+"</strong></p>" +
                        "                            <del>￥"+data['result'][i]['resourceFee']+"</del>" +
                        "                        </a></div>";
                }
                $("#good_course").html(str);
            }
        }
    })
}

// 免费试听课程
function try_course() {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId, "type": 1},
        success: function (data) {
            var code = data.resultCode;
            var result = data.result;
            var str = "";
            if (code == "1") {
                for (var i = 0; i < 1; i++) {
                    str += "<div class=\"media\">" +
                        "                    <a id=" + result[i]['id'] + " onclick='course_info(this)' class='pull-left' style='width: 30%'>" +
                        "                        <img src=\"/img/course-1.jpg\" class=\"try-course media-object order-item-img\">" +
                        "                        </a>" +
                        "                        <div class=\"media-body\">" +
                        "                        <div class=\"order-item-info\">" +
                        "                        <h4 class=\"order-item-title\" style=\"font-weight: bolder\">"+data['result'][i]['courseName']+"</h4>" +
                        "                        <p class=\"order-item-fare\">"+data['result'][i]['titalInfo']+"</p>" +
                        "                    </div>" +
                        "                    </div>" +
                        "                    </div>";
                }
                $("#try_course").html(str);
            }
        }
    })
}

// 所有课程
function all_course() {
    var bussId = $("#bussId").val();
    var url = "/course/getList";
    $.ajax({
        url: url,
        type: "POST",
        data: {"bussId": bussId, "type": null},
        success: function (data) {
            var code = data.resultCode;
            var result = data.result;
            var str = "";
            if (code == "1") {
                for (var i = 0; i < 4; i++) {
                    str += "<li class=\"col-sm-6 col-xs-6 ware-box\">\n" +
                        "                    <a id=" + result[i]['id'] + " onclick='course_info(this)'>\n" +
                        "                        <div class=\"ware-img\">\n" +
                        "                            <img src=\"/img/course-1.jpg\" alt=\"\">\n" +
                        "                            <span class=\"ware-vip\">vip专享</span>\n" +
                        "                        </div>\n" +
                        "                        <h3 class=\"ware-title\">"+data['result'][i]['courseName']+"</h3>\n" +
                        "                        <p class=\"ware-des\">高三 提分 快速</p>\n" +
                        "                        <span class=\"ware-prince red-color\">￥"+data['result'][i]['courseFee']+"</span>\n" +
                        "                    </a>\n" +
                        "                </li>";
                }
                $("#all_course").html(str);
            }
        }
    })
}

// 查询课程详情
function course_info(obj) {
    var course_id = obj.id;
    window.location.href = "/course/getCourse?param1=" + course_id;
}

//  微信分享
function wechatShare(bussId) {
    var title = "";
    var content = "";
    var imgUrl = "";
    $.ajax({
        url: "/jxcIndex/share",
        type: "POST",
        dataType: "json",
        data: "url=" + window.location.href,
        success: function (data) {
            //配置微信信息
            wx.config({
                debug: false,    // true:调试时候弹窗
                appId: data['appId'],  // 微信appid
                timestamp: data['timestamp'], // 时间戳
                nonceStr: data.noncestr,  // 随机字符串
                signature: data['signature'], // 签名
                jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline', 'onMenuShareQQ', 'onMenuShareQZone']
            });
            $.ajax({
                url: "/jxcIndex/getWxShareInfo",
                type: "POST",
                data: {"param1":bussId},
                success: function (data) {
                    title = data.title;
                    content = data.content;
                    imgUrl = "https://yingsu-jxc.oss-cn-shanghai.aliyuncs.com/buss_index/wx_share/"+data.imgUrl
                }
            })
            wx.ready(function () {
                // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
                wx.onMenuShareAppMessage({
                    title: title,
                    desc: content,
                    link: 'http://www.juxuecheng.com',
                    imgUrl: imgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    }
                });
                wx.onMenuShareTimeline({
                    title: title,
                    desc: content,
                    link: 'http://www.juxuecheng.com',
                    imgUrl: imgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    }
                });
                wx.onMenuShareQQ({
                    title: title,
                    desc: content,
                    link: 'http://www.juxuecheng.com',
                    imgUrl: imgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    },
                    cancel: function () {
                    }
                });
                wx.onMenuShareQZone({
                    title: title,
                    desc: content,
                    link: 'http://www.juxuecheng.com',
                    imgUrl: imgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    },
                    cancel: function () {
                    }
                });
            });
            wx.error(function (res) {
                //打印错误消息。及把 debug:false,设置为debug:ture就可以直接在网页上看到弹出的错误提示
            });
        }
    })
}