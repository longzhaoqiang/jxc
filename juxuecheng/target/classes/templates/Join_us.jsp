<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商家入驻</title>
    <meta name="viewport"
          content="width=720,width=device-width,initial-scale=1,initial-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/mobile.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/bootstrap.css"/>
    <link rel="stylesheet" href="../jxc/asset/mobile/css/jxc-index.css"/>
    <script src="../jxc/asset/v2/js/jquery.js"></script>
    <script src="../jxc/asset/swiper-2.0.min.js"></script>
    <!-- 必须引入的文件-->
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <style type="text/css">
        .hide {
            display: none;
        }

        .shade {
            position: fixed;
            top: 0;
            right: 0;
            left: 0;
            bottom: 0;
            background: black;
            opacity: 0.6;
            z-index: 100;
        }

        .add-model {
            position: fixed;
            height: 80%;
            width: 100%;
            top: 0px;
            left: 0%;
            z-index: 101;
            /*background: white;*/
        }
    </style>
</head>
<body style="background: white">
<div class="g-mn" id="weixinshare">
    <div class="row-100">
        <div style="width: 100%">
            <div class="join-img">
                <img style="width: 100%" src="http://image.yingsuit.com/IndexShow/WechatIMG_JOINUS_1.png">
            </div>
            <div class="join-img">
                <img class="join-img-1" src="http://image.yingsuit.com/IndexShow/WechatIMG_JOINUS_2.png">
            </div>
            <div class="join-img" style="margin-top: 40px;">
                <img class="join-img-1" src="http://image.yingsuit.com/IndexShow/WechatIMG_JOINUS_3.png">
            </div>
            <!-- 底部悬浮 -->
            <div style="height: 80px;">

            </div>
        </div>
        <!-- 底部悬浮 -->
        <div class="row-100" onclick="join_us()">
            <div class="join-row x-f xuanfu">
                <div style="background: #ff5500;height: 50px;">
                    <div class="b-m" id="registerMorder" style="padding-top: 12px;margin-top: 0px;">立即入驻
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 微信弹出框 -->
    <div class="shade hide"></div>
    <div class="add-model hide" id="wechatMorder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="wechat-window" role="document">
            <div style="border: 1px solid #ccc;">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal" aria-label="Close" style="margin-top: 2px;">
                        <span onclick="close()">关闭</span></button>
                    <h4 class="modal-title" id="myModalLabel">联系我们获取账号</h4>
                </div>
                <div class="modal-body">
                    <div>
                        <img id="wechatCode" src="http://image.yingsuit.com/IndexShow/WechatIMG_JoinUs_wechat.jpeg"
                             style="width: 100%"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script src="../jxc/asset/mobile/js/bootstrap.min.js"></script>
<script>
    window.onload = function () {
        var pos = $('#div1').offset();// offset() 获得div1当前的位置，左上角坐标(x,y)                
        $(window).scroll(function () { //滚动条滚动事件                    
            if ($(this).scrollTop() > pos.top) {
                $('#div1').css('width', '100px').css('top', $(this).scrollTop() - pos.top);
            } else if ($(this).scrollTop() <= pos.top) {
                $('#div1').css('width', '100x').css('top', 0).css('position', 'relative');
            }
        })
    };


    $(function () {
        wechatShare()
        $(".close").on('click', function () {
            $('.shade, .add-model').removeClass('shade');
            $('.shade, .add-model').addClass('hide');
            window.location.reload();
        })
    })

    function join_us() {
        $('.shade, .add-model').removeClass('hide');
    }

    //  微信分享
    function wechatShare() {
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
                        title: '机构招生，就来聚学城！',
                        desc: '入驻聚学城，让您的招生传播扩大10倍！',
                        link: 'http://www.juxuecheng.com',
                        imgUrl: 'http://image.yingsuit.com/Logo/Logo_50001_jxclogo-08.jpg',
                        type: 'link',
                        success: function () {
                            //成功之后的回调
                        }
                    });
                    wx.onMenuShareTimeline({
                        title: '入驻聚学城，让您的招生传播扩大10倍！',
                        link: 'http://www.juxuecheng.com',
                        imgUrl: 'http://image.yingsuit.com/Logo/Logo_50001_jxclogo-08.jpg',
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

</script>
</body>
</html>

