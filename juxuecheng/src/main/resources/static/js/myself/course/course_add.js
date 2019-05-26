// 轮播
$(function () {
    // 顶部轮播图
    var mySwiper = new Swiper ('.swiper-container', {
        // 如果需要分页器
        autoplay:true,
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
        autoplay:true,
        delay: 5000,
        direction: 'vertical'
    });
})

// 提交
function commit() {
    $.ajax({
        url: "/course/addCourse",
        type: "POST",
        data: $("#courseForm").serialize(),
        success: function (data) {
            var code = data.resultCode;
            var msg = data.resultMsg;
            if (code == "1"){
                window.location.href = "/course";
            } else {
                alert(msg);
            }
        },
    });
}

function wechatShare(courseId) {
    var bussesserName = "";
    var courseName = "";
    var bussImgUrl = "";
    var courseIntraduce = "";
    $.ajax({
        url: "/jxcIndex/getOneCourse",
        type: "POST",
        data: {"courseId": courseId},
        success: function (data) {
            bussesserName = data['courseMap']['bussesserName'];
            courseName = data['courseMap']['courseName'];
            courseIntraduce = data['courseMap']['courseIntraduce'];
            bussImgUrl = "http://image.yingsuit.com/Logo/" + data['courseMap']['uploadLogo'];
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
                    title: '聚学城-' + courseName,
                    desc: courseIntraduce,
                    link: window.location.href,
                    imgUrl: bussImgUrl,
                    type: 'link',
                    success: function () {
                        //成功之后的回调
                    }
                });
                wx.onMenuShareTimeline({
                    title: '来自聚学城-' + courseName,
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