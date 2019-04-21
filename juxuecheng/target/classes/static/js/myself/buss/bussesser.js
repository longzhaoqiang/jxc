$(function () {
    checkLogin();

    var bussesser_id = getUrlParam('bussesserId');
    var paramArr = bussesser_id.split(",");
    var bussesserId = paramArr[0];
    var distance = paramArr[1];
    bussesserIndexMsg(bussesserId, distance);
    wechatShare(bussesserId);
    // 点击机构介绍或课程时修改样式
    $("#mouse-1").css("background-color", "coral");
    $("#mouse-color-1").css("color", "white");
    $("#mouse-color-2").css("color", "#1F1F1F");
    $("#mouse-2").mouseover(function () {
        $("#mouse-2").css("background-color", "coral");
        $("#mouse-1").css("background-color", "white");
        $("#mouse-color-2").css("color", "white");
        $("#mouse-color-1").css("color", "#1F1F1F");
        $("a").css("text-decoration", "none");
    });
    $("#mouse-1").mouseover(function () {
        $("#mouse-1").css("background-color", "coral");
        $("#mouse-2").css("background-color", "white");
        $("#mouse-color-1").css("color", "white");
        $("#mouse-color-2").css("color", "#1F1F1F");
    });

    // 点击课程时隐藏机构介绍
    $("#course").hide();

    $(".courseNo1").click(function () {
        var val = $(this).attr("id");
        alert(val);
    })
})

// 点击机构介绍
function bussintra_click() {
    $("#course").hide();
    $("#bussesserIntraduction").show();
}

// 点击课程
function course_click() {
    var bussesser_id = getUrlParam('bussesserId');
    var paramArr = bussesser_id.split(",");
    var bussesserId = paramArr[0];
    $("#bussesserIntraduction").hide();
    $("#course").show();
    getCourseList(bussesserId);
}

// 获取url参数
function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r != null) return unescape(r[2]);
    return null;
}

// 商家首页
function bussesserIndexMsg(bussesserId, distance) {
    $.ajax({
        url: "/jxcIndex/bussesserIndex",
        type: "POST",
        data: {"bussesserId": bussesserId},
        success: function (data) {

            var bussesserResult = data['bussesserMap'];
            var courseResult = data['courseMap'];
            var teacherResult = data['teacherMap'];
            var pictrueResult = data['pictrueList'];
            var classType = bussesserResult['classType']
            var teacherPowerStr = "";
            var pictrueStr = "";
            bussImgUrl = "http://image.yingsuit.com/Logo/" + bussesserResult['uploadLogo'];
            bussesserName = bussesserResult['bussesserName'];
            bussesserIdea = bussesserResult['bussesserIdea'];
            $("title").html(bussesserResult['bussesserName'] + "-聚学城");
            // 加载商家首页图片
            if (pictrueResult.length > 0) {
                $("#bussImg_1").attr('src', 'http://image.yingsuit.com/BussImg/' + pictrueResult[0]);
            } else {
                $(".index-banner").hide();
            }
            $("#bussImg").attr('src', bussImgUrl);
            $("#bussesserName").text(bussesserName);
            $("#callPhone").attr('href', 'tel:' + bussesserResult['phone']);
            $("#callPhone").text(bussesserResult['phone']);
            $("#distance").text(distance);
            $("#bussesserIdea").text(bussesserIdea);
            $("#address").text(bussesserResult['address']);
            //$("#phone").text(bussesserResult['phone']);
            $("#mouse-color-2").text("课程(" + courseResult.length + ")");
            $("#bussesserIntraduce").text(bussesserResult['bussesserIntroduce']);
            $("#tearchPower").text(bussesserResult['tearchPower']);

            // 师资力量
            for (var i = 0; i < teacherResult.length; i++) {
                teacherPowerStr += "<div class='row row-11'><div style='width: 30%;height: 80px;float: left;'>";
                if (teacherResult[i]['teacherLogo'] == null || teacherResult[i]['teacherLogo'] == "") {
                    teacherPowerStr += "<img class='img-border row-left-10' src='http://image.yingsuit.com/TeacherImg/defaulTeacher.jpeg' style='width: 70px;'/>";
                } else {
                    teacherPowerStr += "<img class='img-border row-left-10' src='http://image.yingsuit.com/TeacherImg/" + teacherResult[i]['teacherLogo'] + "' style='width: 70px;'/>";
                }
                teacherPowerStr += "</div><div style='width: 70%;float: left;line-height: 1.5;'>" +
                    "<div><span class='font-size-16-1 color-1'>" + teacherResult[i]['teacherName'] + "</span></div>" +
                    "<div class='intraduce-1' style='padding-right: 23px;color: #737373;'>" +
                    "<span>" + teacherResult[i]['teacherIntroduce'] + "</span></div>" +
                    "</div></div>" +
                    "<div style='margin-top: 5px;'></div>";
            }
            $("#teacherPowerList").html(teacherPowerStr);

            for (var i = 0; i < pictrueResult.length; i++) {
                pictrueStr += "<li class=''>" +
                    "<img class='row-top10 row-100' src='http://image.yingsuit.com/BussImg/" + pictrueResult[i] + "'></li>";
            }
            $("#bussesserImg").html(pictrueStr);
            // 显示附近机构
            getBussesserList(bussesserId, classType);
        },
        error: function (data) {
            alert("网络异常");
        }
    })
}

// 显示课程列表
function getCourseList(bussesserId) {
    $.ajax({
        url: "/jxcIndex/bussesserIndex",
        type: "POST",
        data: {"bussesserId": bussesserId},
        success: function (data) {
            var courseResult = data['courseMap'];
            var courseStr = "";
            for (var i = 0; i < courseResult.length; i++) {
                courseStr += "<div class='row-1'>" +
                    "<li class='has-cover'>" +
                    "<img class='img-border cover' src='http://image.yingsuit.com/SubjectImg/" + courseResult[i]['subjectImg'] + "'/>" +
                    "<a class='title row-top-10' onclick='javascript:getCourseId(this)' style='width: 140%' id='" + courseResult[i]['id'] + "'><h5>" + courseResult[i]['courseName'] + "</h5></a>" +
                    "<span class='desc'>开课时间：" + courseResult[i]['courseBeginTime'] + "</span>" +
                    "<span class='desc' style='margin-top: 10px;'>课程费用：￥" + courseResult[i]['courseFee'] + "</span>" +
                    "</li>" +
                    "<HR style='width:98%;text-align: center;' class='border-1 top-bottom-10' SIZE=1>" +
                    "</div>";
            }
            $("#courseList").html(courseStr);
        },
        error: function (data) {
            alert("网络异常");
        }
    })
}

function getCourseId(obj) {
    var courseId = obj.id;
    window.location.href = "../jxc/course_add.html?courseId=" + courseId;
}

// 微信分享
function wechatShare(bussesserId) {
    var bussesserName = "";
    var bussesserIdea = "";
    var bussImgUrl = "";
    $.ajax({
        url: "/jxcIndex/getOneBussesser",
        type: "POST",
        data: {"bussesserId": bussesserId},
        success: function (data) {
            var resultMsg = data['resultMsg'];
            if (resultMsg == "1") {
                bussesserName = data['bussesserMap']['bussesserName'];
                bussesserIdea = data['bussesserMap']['bussesserIdea'];
                bussImgUrl = "http://image.yingsuit.com/Logo/" + data['bussesserMap']['uploadLogo'];
            } else {
                alert("网络异常");
            }
        },
    });
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
                jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline']
            });
            wx.ready(function () {
                // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
                wx.onMenuShareAppMessage({
                    title: '聚学城为您推荐-' + bussesserName,
                    desc: bussesserIdea,
                    link: window.location.href,
                    imgUrl: bussImgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    }
                });
                wx.onMenuShareTimeline({
                    title: bussesserName + '来自于聚学城',
                    link: window.location.href,
                    imgUrl: bussImgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    }
                });
            });
            wx.error(function (res) {
                //打印错误消息。及把 debug:false,设置为debug:ture就可以直接在网页上看到弹出的错误提示
            });
        }
    })
}

// 查询商家列表
function getBussesserList(bussesserId, classType) {
    $.ajax({
        url: "/jxcIndex/recommendBuss",
        type: "POST",
        data: {"bussesserId": bussesserId, "classType": classType},
        success: function (data) {
            var result = data['bussesserMapList'];
            var recommendStr = "";
            var buttonStr = "";
            for (var i = 0; i < result.length; i++) {
                var classType = result[i]['classType'];
                var distance = result[i]['distance'];
                if (classType != null) {
                    var typeArray = classType.split("|");
                    for (var j = 0; j < typeArray.length; j++) {
                        buttonStr += "<button type='button' disabled class='btn btn-default btn-lg bt-1' style='border-color: #ff5500;margin-right: 5px;border-radius: 3px;'>" +
                            "<span class='glyphicon' aria-hidden='true' style='color: #ff5500;'>" + typeArray[j] + "</span></button>";
                    }
                }
                recommendStr += "<div class='row row-11 row-100'><a id='" + result[i]['id'] + "' style='width:100%' onclick='javascript:getBussesserId(this)'>" +
                    "<div style='width: 30%;height: 80px;float: left;'>" +
                    "<img class='img-border row-left' src='http://image.yingsuit.com/Logo/" + result[i]['uploadLogo'] + "' style='width: 70px;'/>" +
                    "</div>" +
                    "<div style='width: 70%;float: left;line-height: 1.5;'>" +
                    "<div><span class='font-size-16-1 color-1'>" + result[i]['bussesserName'] + "</span></div>" +
                    "<div class='row-top-5' style='width: 100%'><div style='width: 70%;float: left;color: #737373;'>距离我：</div><div style='width: 20%;float: left;color: dodgerblue'>" + distance + "km</div></div>" +
                    "<div class='intraduce-1 row-top-5' style='padding-right: 23px;color: #737373;'>" +
                    "<span class='desc' style='font-size: 12px;'>" + result[i]['address'] + "</span></div>" +
                    "</div></a>" +
                    "<div style='margin-top: 5px;'>" +
                    "<div class='row-top-5 row-left'>" + buttonStr + "</div></div></div>" +

                    "<HR style='width:98%;text-align: center;' color='#987cb9' SIZE=1>";
                buttonStr = "";
            }
            $("#recommend").html(recommendStr);
            $("#bottom_hint").html("已为您加载完成");
        },
        error: function (data) {
            // location.reload();
        }
    })
}

function getBussesserId(obj) {
    var bussesserId = obj.id;
    var distance = obj.name;
    window.location.href = "../jxc/bussesserIndex.html?bussesserId=" + bussesserId + "," + distance;
}

// 检查是否登录
function checkLogin() {
    var url = "/user/checkLogin";
    $.ajax({
        type: "POST",
        url: url,
        //async: false,
        success: function (data) {
            var code = data.resultCode;
            if (code == -1){
                window.location.href="/login";
            }
        },
        error: function (e) {
            alert(e);
        }
    })
}