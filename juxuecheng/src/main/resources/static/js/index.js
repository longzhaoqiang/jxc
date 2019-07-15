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
    var bussId = $("#bussId").val();
    init(bussId);

    $("#buss_index").click(function () {
        window.location.href = "/buss_index?bussId="+bussId;
    });

})

function home_openid() {
    window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa2fbe7ca7c970259&redirect_uri=http://www.juxuecheng.com/user/home/&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    // window.location.href = "/home";
}

function init(bussId) {
    var url = "/index/main";
    $.ajax({
        url : url,
        type: "POST",
        data:{"bussId":bussId},
        success: function (data) {
            var teacherModel = data.teacherModel;
            var teacherModelStr = "";
            for (var i = 0;i<teacherModel.length;i++){
                teacherModelStr += "<div class='swiper-slide seckill-ware' style='width: 110.714px;margin-right: 5px;'>" +
                    "                        <a href='item.html'>" +
                    "                            <div><img style='height: 110.714px' src='http://image.yingsuit.com/TeacherImg/"+teacherModel[i]["teacherLogo"]+"'/></div>" +
                    "                            <p style='font-size: 14px'>"+teacherModel[i]['teacherName']+"</p>" +
                    "                            <p style='font-size: 14px;font-weight: 700;color: #ff5500'>"+teacherModel[i]['teachDate']+"年教龄</p>" +
                    "                            <p class='o-1' style='' font-weight: inherit;font-size: 14px;'>"+teacherModel[i]['teacherSubject']+"</p>" +
                    "                        </a>\n" +
                    "                    </div>";
            }
            $("#teacherList").html(teacherModelStr);
        }
    })
}