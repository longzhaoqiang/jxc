<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商家列表</title>
    <meta name="viewport"
          content="width=720,width=device-width,initial-scale=1,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/mobile.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/bootstrap.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/jxc-index.css"/>
    <script src="../jxc/asset/v2/js/jquery.js"></script>
    <style type="text/css">
        .bg {
            background-color: whitesmoke;
        }

        .bg:hover {
            background-color: coral;
            color: white;
        }

        .bg-position {
            background-color: white;
        }

        .bg-position:hover {
            background-color: coral;
            color: white;
        }
    </style>
</head>
<body>
<div class="g-mn" id="weixinshare">
    <div class="m-box m-0" id="bussesser">
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
    <div style="width: 100%" id="galder">
        <!-- 区域导航栏 -->
        <div id="direction" style="width: 35%;text-align: center;position: fixed">

        </div>
        <!-- 具体位置导航栏 -->
        <div id="position" style="width: 65%;margin-left: 35%;background: whitesmoke;text-align: center">

        </div>
    </div>
</div>
<script src="../jxc/asset/mobile/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=SZGylKjObBUnGLMnfVnSv47Xi0Z5mBAK">
    //v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
</script>
<script>
    $(function () {
        $("#bussesser").hide();
        $("title").html("按区域位置筛选");
        $.ajax({
            url: "/bussesser/getDirection",
            type: "POST",
            success: function (data) {
                var result = data['directionList'];
                var directionStr = "";
                for (var i = 0; i < result.length; i++) {
                    directionStr += "<div class='bg' id='" + result[i]['id'] + "' onclick='direction(this)' style='height: 60px;padding-top: 18px;'><div style='font-size: 16px;'>" + result[i]['direction_name'] + "</div></div>";
                }
                $("#direction").html(directionStr);
                $('#1').css('background-color', 'coral');
                $('#1').css('color', 'white');
                // 首次进来时自动加载地址
                direction(null);
            },
            error: function (data) {
                alert();
            }
        })

    })

    // 点击左边区域时
    function direction(obj) {
        var directionNo = "";
        if (obj != null) {
            directionNo = '00' + obj.id;
            if (obj.id != "1") {
                $('#1').css('background-color', 'whitesmoke');
                $('#1').css('color', '#333');
            } else {
                $('#1').css('background-color', 'coral');
                $('#1').css('color', 'white');
            }
        } else {
            directionNo = '001';
        }
        $.ajax({
            url: "/bussesser/getPosition",
            type: "POST",
            dataType: "JSON",
            data: {"directionNo": directionNo},
            success: function (data) {
                var result = data['directionList'];
                var allPosition = result[0]['position_name'];
                allPosition = allPosition.substr(1, 2);
                var positionStr = "<div class='bg-position' id='" + allPosition + "区' onclick='searchBuss(this)' style='height: 40px;padding-top: 10px;'>" + '全' + allPosition + "</div>";
                for (var i = 1; i < result.length; i++) {
                    positionStr += "<div class='bg-position' id='" + result[i]['position_name'] + "' onclick='searchBuss(this)' style='height: 40px;padding-top: 10px;'>" + result[i]['position_name'] + "</div>";
                }
                $("#position").html(positionStr);
            }
        })
    }

    function searchBuss(obj) {
        var positionName = obj.id;
        $.ajax({
            url: "/jxcIndex/getLngLat",
            type: "POST",
            success: function (data) {
                if (data == null || data == "") {
                    openMap();
                } else {
                    getBussesserList(positionName);
                    $("title").html("符合您条件的商家");
                }
            },
            error: function () {

            }
        })
    }

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

    function getBussesserList(positionName) {
        $.ajax({
            url: "/bussesser/getBussesserListBySit",
            type: "POST",
            data: {"positionName": positionName},
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
                $("#bottom_hint").html("已为您加载完成");

                $("#galder").hide();
                $("#bussesser").show();
            },
            error: function (data) {
                alert("请重新刷新网页");
            }
        })
    }

    function getBussesserId(obj) {
        var bussesserId = obj.id;
        window.location.href = "../jxc/bussesserIndex.html?bussesserId=" + bussesserId;
    }
</script>
</body>
</html>
