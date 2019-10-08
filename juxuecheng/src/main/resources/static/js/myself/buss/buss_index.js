$(function () {
    // 查看是否编辑过商家首页
    var bussId = $("#bussId").val();
    if (bussId == null){
        bussId = 124;
    }
    $.ajax({
        url: "/buss/getBussInfo",
        data: {"bussId":bussId},
        type: "POST",
        success: function (data) {
            var code = data['resultCode'];
            var imgStr = "";
            if (code == "1"){
                var buss_intro = data['result']['bussIntroduce'];
                var teacher_power = data['result']['tearchpower'];
                var urls = data['result']['picUrl'];
                var strs = new Array(); //定义一数组
                strs = urls.split(","); //字符分割
                var pic_str = "";

                $("#phone").html(data['result']['phone']);
                $("#address").html(data['result']['address']);
                buss_intro =  buss_intro.replace(/\n|\r\n/g,'<br/>');
                teacher_power = teacher_power.replace(/\n|\r\n/g,'<br/>');
                $("#intraduce").html(buss_intro);
                $("#teacher_power").html(teacher_power);
                for (var i=0;i<strs.length ;i++ )
                {
                    pic_str += "<img style='margin-bottom: 2%' class='ml-2' src='https://yingsu-jxc.oss-cn-shanghai.aliyuncs.com/buss_index/" + strs[i] + "'>";
                }
                $("#buss_pic").html(pic_str);
            }
        }
    })
   // 微信分享功能
   wechatShare(bussId);
})

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
               link: 'http://www.juxuecheng.com/index?bussId='+bussId,
               imgUrl: imgUrl,
               type: 'link',
               success: function () {
                  //成功之后的回调
               }
            });
            wx.onMenuShareTimeline({
               title: title,
               desc: content,
               link: 'http://www.juxuecheng.com/index?bussId='+bussId,
               imgUrl: imgUrl,
               type: 'link',
               success: function () {
                  //成功之后的回调
               }
            });
            wx.onMenuShareQQ({
               title: title,
               desc: content,
               link: 'http://www.juxuecheng.com/index?bussId='+bussId,
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
               llink: 'http://www.juxuecheng.com/index?bussId='+bussId,
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