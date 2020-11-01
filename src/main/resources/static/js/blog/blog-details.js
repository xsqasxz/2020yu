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
});