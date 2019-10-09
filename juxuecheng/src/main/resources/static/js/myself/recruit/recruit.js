$(function (){
   $("#null_data").show();
   var bussId = $("#bussId").val();
   $.ajax({
      url: '/recruit/getList',
      type: 'POST',
      data: {"bussId":bussId},
      success: function (data){
         var str = "";
         if (data.result.length > 0){
            $("#null_data").hide();
            $("#add-recruit").show();
         } else{
            $("#data-title").hide();
            $("#add-recruit").hide();
         }
         for(var i = 0;i<data.result.length;i++){
            str += "<div style='padding: 15px;'><a id=''>" +
                  "                    <div style=\"line-height: 1.5;\">\n" +
                  "                        <div>\n" +
                  "                            <span class=\"font-size-16-1 color-1\" id=\"title\">"+data.result[i].recruitTitle+"</span>\n" +
                  "                            <button type=\"button\" class=\"recruit-btn\" id=\"recruit_salary\">"+data.result[i].recruitSalary+"</button>\n" +
                  "                        </div>\n" +
                  "                        <div style=\"margin-top: 3px\">\n" +
                  "                            <span id=\"recruit_address\">"+data.result[i].recruitAddress+"</span>\n" +
                  "                        </div>\n" +
                  "                        <div style=\"margin-bottom: 5px;\">\n" +
                  "                            <div class=\"recruit-border\">\n" +
                  "                                <span class=\"font-color-1 recruit-remark\" id=\"experence\">"+data.result[i].experence+"</span>\n" +
                  "                            </div>\n" +
                  "                            <div class=\"recruit-border\">\n" +
                  "                                <span class=\"font-color-1 recruit-remark\" id=\"education\">"+data.result[i].education+"</span>\n" +
                  "                            </div>\n" +
                  "                        </div>\n" +
                  "                    </div>\n" +
                  "                </a>\n" +
                  "            </div>\n" +
                  "            <hr style=\"border-top: 5px solid #eee;margin-bottom: 0px\" width=\"100%\" color=\"#6f5499\"/>";
         }
         $("#recruit-list").html(str);
      }
   })
})

function add() {
    var bussId = $("#bussId").val();
    window.location.href = "/recruit_add?param1="+bussId;
}