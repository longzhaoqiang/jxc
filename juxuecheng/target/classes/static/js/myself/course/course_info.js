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

    var courseName = $("#courseName").val();
    var titalInfo = $("#titalInfo").val();
    var courseFee = $("#courseFee").val();
    var courseIntraduce = $("#courseIntraduce").val();
    var applyStudent = $("#applyStudent").val();
    var courseGoal = $("#courseGoal").val();
    var courseContent = $("#courseContent").val();
    var courseSpecial = $("#titalInfo").val();
    courseIntraduce = courseIntraduce.replace(/\n|\r\n/g,'<br/>');
    applyStudent = applyStudent.replace(/\n|\r\n/g,'<br/>');
    courseGoal = courseGoal.replace(/\n|\r\n/g,'<br/>');
    courseContent = courseContent.replace(/\n|\r\n/g,'<br/>');
    courseSpecial = courseSpecial.replace(/\n|\r\n/g,'<br/>');
    $("#course_name").html(courseName);
    $("#tital_info").html(titalInfo);
    $("#course_fee").html(courseFee);
    $("#course_intraduce").html(courseIntraduce);
    $("#apply_student").html(applyStudent);
    $("#course_goal").html(courseGoal);
    $("#course_content").html(courseContent);
    $("#course_special").html(courseSpecial);
})