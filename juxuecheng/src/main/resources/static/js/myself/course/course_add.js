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