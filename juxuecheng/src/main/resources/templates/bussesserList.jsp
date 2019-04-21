<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商家列表</title>
    <meta name="viewport"
          content="width=720,width=device-width, initial-scale=1,  initial-scale=1.0,maximum-scale=1.0, user-scalable=no;"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/mobile.css?v=2"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/bootstrap-1.css">
    <link rel="stylesheet" href="../jxc/asset/mobile/css/jxc-index.css"/>
    <script src="../jxc/asset/v2/js/jquery.js"></script>
    <script src="../jxc/asset/swiper-2.0.min.js"></script>
    <!-- 必须引入的文件-->
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background: white">
<div class="g-mn">
    <div class="m-box m-0">
        <div class="m-box-hd">
            <div class="tt" id="title_1"></div>
        </div>
        <div class="m-box-mn">
            <ul class="m-game-list" id="bussList">

            </ul>
        </div>
        <div class="g-ft f-cb">
            <div style="text-align: center">
                <span id="bottom_hint">正在为您加载数据... ...</span>
            </div>
        </div>
    </div>
</div>


<div id="JS-share-page" class="m-layer m-layer-show">
    <div class="lymask"></div>
    <table class="lytable">
        <tbody>
        <tr>
        </tr>
        </tbody>
    </table>
</div>
<script src="../jxc/asset/mobile/js/mobile.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SZGylKjObBUnGLMnfVnSv47Xi0Z5mBAK"></script>
</body>
</html>
<script type="text/javascript">

    $(function () {

        $.ajax({
            url: "/jxcIndex/getLngLat",
            type: "POST",
            success: function (data) {
                if (data == null || data == "") {
                    openMap();
                } else {
                    getBussesserList();
                }
            },
            error: function () {

            }
        })

    })

    function openMap() {
        var geolocation = new BMap.Geolocation();
        // 开启SDK辅助定位
        geolocation.enableSDKLocation();
        geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                var lng = r.point.lng;
                var lat = r.point.lat;
                $.ajax({
                    url: "/jxcIndex/currentSite",
                    type: "POST",
                    dataType: 'json',
                    data: {"lng": lng, "lat": lat},
                    success: function (data) {

                    },
                    error: function () {

                    },
                })
            }
        });
    }

    function getBussesserList() {
        var classType = getUrlParam('classType');
        var className = castClassType(classType, className);

        $.ajax({
            url: "/bussesser/getBussesserList",
            type: "POST",
            data: {"classType": classType},
            success: function (data) {
                var result = data['bussesserMap'];
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
                    recommendStr += "<div class='row row-11 row-100'><a id='" + result[i]['id'] + "' onclick='javascript:getBussesserId(this)'>" +
                        "<div style='width: 30%;height: 80px;float: left;'>" +
                        "<img class='img-border row-left' src='http://image.yingsuit.com/Logo/" + result[i]['uploadLogo'] + "' style='width: 70px;'/>" +
                        "</div>" +
                        "<div style='width: 70%;float: left;line-height: 1.5;'>" +
                        "<div><span class='font-size-16-1 color-1'>" + result[i]['bussesserName'] + "</span></div>" +
                        "<div class='row-top-5' style='width: 100%'><div style='width: 70%;float: left;color: #737373;'>距离我：</div><div style='width: 20%;float: left'>" + distance + "km</div></div>" +
                        "<div class='intraduce-1 row-top-5' style='padding-right: 23px;color: #737373;'>" +
                        "<span class='desc' style='font-size: 12px;'>" + result[i]['address'] + "</span></div>" +
                        "</div></a>" +
                        "<div style='margin-top: 5px;'>" +
                        "<div class='row-top-5 row-left'>" + buttonStr + "</div></div></div>" +

                        "<HR style='width:98%;text-align: center;' color='#987cb9' SIZE=1>";
                    buttonStr = "";
                }
                $("#bussList").html(recommendStr);

                $("#title_1").html("已为您搜索到<span style='color: coral'>" + result.length + "</span>家<span style='color: coral'>" + className + "</span>教育机构");
                $("#bottom_hint").html("已为您加载完成");
            },
            error: function (data) {
                alert("请重新刷新网页");
            }
        })
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

    function getBussesserId(obj) {
        var bussesserId = obj.id;
        window.location.href = "../jxc/bussesserIndex.html?bussesserId=" + bussesserId;
    }

    function castClassType(classType, className) {
        if (classType == "1") {
            className = '小学';
        } else if (classType == "2") {
            className = '初中';
        } else if (classType == "3") {
            className = '高中';
        } else if (classType == "4") {
            className = '美术';
        } else if (classType == "5") {
            className = '音乐/乐器';
        } else if (classType == "6") {
            className = '托管';
        }
        return className;
    }
</script>
