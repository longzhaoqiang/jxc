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
})