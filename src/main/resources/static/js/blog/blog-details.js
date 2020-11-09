$(function() {
    let frontHtmlId = $("#htmlId").val();
    $(".am-pagination-prev").click(function(){
        $.post("/f/paginationPrev",{
            frontHtmlId:frontHtmlId
        },function(data){
            if(data.success){
                window.location.href="details"+data.data+".html";
            }
        });

    });
    $(".am-pagination-next").click(function(){
        $.post("/f/paginationNext",{
            frontHtmlId:frontHtmlId
        },function(data){
            if(data.success){
                window.location.href="details"+data.data+".html";
            }
        });
    });
    //默认隐藏A标签下面的small
    $("a[class='a_click'] small").hide();
    $("a[class='a_click']").click(function(){
        $(this).children("small").toggle();
    });

});