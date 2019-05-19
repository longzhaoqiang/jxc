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

    var courseIntraduce = $("#courseIntraduce").val();
    var applyStudent = $("#applyStudent").val();
    var courseGoal = $("#courseGoal").val();
    var courseContent = $("#courseContent").val();
    var courseSpecial = $("#courseSpecial").val();
    $("#course_intraduce").html(courseIntraduce);
    $("#apply_student").html(applyStudent);
    $("#course_goal").html(courseGoal);
    $("#course_content").html(courseContent);
    $("#course_special").html(courseSpecial);
})

// 提交
function commit() {
    $.ajax({
        url: "/course/update",
        type: "POST",
        data: $("#courseForm").serialize(),
        success: function (data) {
            var code = data.resultCode;
            if (code == "1"){
                window.location.href = "/course";
            } else {
                alert("网络异常！请稍后再试");
            }
        },
    });
}
