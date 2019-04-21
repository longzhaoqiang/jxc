$('.table-sort').dataTable({
    "aaSorting": [[1, "desc"]],//Ĭ�ϵڼ�������
    "bStateSave": true,//״̬����
    "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //�����е�������ʾ
        {"orderable": false, "aTargets": [0, 6]}// �ƶ��в���������
    ]
});

function updata_stata(title, url, w, h) {
    layer_show(title, url, w, h);
}

$(function () {

    var bussesserStr = "";
    $.ajax({
        url: "/bussesser/getBussListHt",
        type: "POST",
        success: function (data) {
            var bussesserlist = data["bussesserMap"];
            var bussesserListLength = bussesserlist.length;
            for (var i = 0; i < bussesserListLength; i++) {
                bussesserStr += "<tr class='text-c'><td><input name='' type='checkbox' value=''></td>" +
                    "<td>" + data["bussesserMap"][i]['id'] + "</td>" +
                    "<td><input type='text' class='input-text text-c' value=" + data["bussesserMap"][i]['bussesserName'] + "></td>" +
                    "<td>" + data["bussesserMap"][i]['address'] + "</td>" +
                    "<td class='text-l'>" + data["bussesserMap"][i]['createTime'] + "</td>" +
                    "<td class='text-l'>" + data["bussesserMap"][i]['mobile'] + "</td>" +
                    "<td>FUFEI</td>" +
                    "<td><input type='text' class='input-text text-c' value='3'></td>" +
                    "<td class='f-14 product-brand-manage'>" +
                    "<a style='text-decoration:none' onClick='product_brand_edit('Ʒ�Ʊ༭','codeing.html','1')' title='������ҳ'><i class='Hui-iconfont'>COPY </i></a>" +
                    "<a style='text-decoration:none' onClick='product_brand_edit('Ʒ�Ʊ༭','codeing.html','1')' title='��ӿγ�'><i class='Hui-iconfont'>ADD</i></a>" +
                    "<a style='text-decoration:none' class='ml-5' onClick='updata_stata('�̼�״̬���','updata_stata.jsp','600','328.5')' title='���״̬'><i class='Hui-iconfont'>Atler</i></a>" +
                    "</td></tr>";
            }
            $("#tr_bussesser").html(bussesserStr);
        }
    });


})