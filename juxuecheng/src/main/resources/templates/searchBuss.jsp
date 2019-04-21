<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>机构搜索</title>
    <meta name="viewport"
          content="width=720,width=device-width,initial-scale=1,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/mobile.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/bootstrap.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/jxc-index.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<div class="g-mn">
    <div class="head-1 s-w">
        <div class="head-2 s-w">
            <img class="row-left" src="http://image.yingsuit.com/Logo/Logo_search.jpeg" style="height: 35px">
            <div class="search-1">
                <div style="float: left;width: 80%">
                    <input type="text" class="search-input" id="bussesserName" autofocus="autofocus"
                           placeholder="请输入机构名"/>
                </div>
                <div style="width: 10%;float: left" id="search">
                    <img class="searcher-pic" onclick="searchBuss()"
                         src="http://image.yingsuit.com/IndexShow/search.png"/>
                </div>
            </div>
        </div>
    </div>
    <div class="recommend s-w">
        <div style="margin-top: 10px;">
            <span style="padding-left: 20px;padding-left: 20px;color: #9c9c9c">热门搜索</span>
        </div>
        <div style="margin-top: 10px;">
            <a href="../jxc/bussesserList.jsp?classType=1">
                <button type="button" class="bt btn">小 学</button>
            </a>
            <a href="../jxc/bussesserList.jsp?classType=2">
                <button type="button" class="bt btn">初 中</button>
            </a>
            <a href="../jxc/bussesserList.jsp?classType=3">
                <button type="button" class="bt btn">高 中</button>
            </a>
            <a href="../jxc/bussesserList.jsp?classType=4">
                <button type="button" class="bt btn">美 术</button>
            </a>
        </div>
        <div style="margin-top: 10px;">
            <a href="../jxc/bussesserList.jsp?classType=5">
                <button type="button" class="bt btn">音 乐</button>
            </a>
            <a href="../jxc/bussesserList.jsp?classType=6">
                <button type="button" class="bt btn">拖 管</button>
            </a>
        </div>
    </div>
    <div class="content1 s-w" id="bussesser">
        <div id="bussFiled">
            <ul class="m-game-list" id="bussList">
            </ul>
        </div>
        <div class="g-ft f-cb" id="hint">
            <div style="text-align: center">
                <span id="bottom_hint">正在为您加载数据... ...</span>
            </div>
        </div>
    </div>
</div>
<script src="../jxc/asset/v2/js/jquery.js"></script>
<script src="../jxc/asset/mobile/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("body").css('background-color', 'white');
        $("#hint").css('display', 'none');
        $("#bussesserName").focus();
        $("#bussesser").css('display', 'none');
    })

    function searchBuss() {
        var bussesserName = $("#bussesserName").val();

        $.ajax({
            url: "/jxcIndex/getLngLat",
            type: "POST",
            success: function (data) {
                if (data == null || data == "") {
                    openMap();
                } else {
                    getBussesserList(bussesserName);
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

    function getBussesserList(bussesserName) {
        $.ajax({
            url: "/jxcIndex/getBussesserList",
            type: "POST",
            data: {"bussesserName": bussesserName},
            success: function (data) {
                var result = data['bussesserList'];
                var recommendStr = "";
                var buttonStr = "";
                if (result.length > 0) {
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
                } else {
                    alert("抱歉！暂时没有您要的机构");
                    $("#bussesserName").val("");
                    return false;
                }

                $("#bussList").html(recommendStr);
                $("#bussFiled").css("padding-top", '20px');
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
