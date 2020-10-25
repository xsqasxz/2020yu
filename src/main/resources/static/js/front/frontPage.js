$(function() {
    const $modal = $('#my-update-front-page');
    let clickTr;
    $(".js-front-page-update").click(function(){
        clickTr = this;
        const frontHtmlId = $(this).val();
        const htmlName = $(this).parents("tr").children("td.table-html-name").html();
        // const htmlType = $(this).parents("tr").children("td.table-html-type").html();
        const htmlKeyword = $(this).parents("tr").children("td.table-html-keyword").html();
        const htmlUrl = $(this).parents("tr").children("td.table-html-url").html();
        $("#frontHtmlId").val(frontHtmlId);
        $("#htmlName").val(htmlName);
        $("#htmlKeyword").val(htmlKeyword);
        $("#htmlUrl").val(htmlUrl);
        $modal.modal();
    });

    $modal.find('.am-btn').on('click', function(e) {
        const $target = $(e.target);
        if (($target).hasClass('js-modal-close')) {
            //关闭添加窗口
            modalClose();
        }else if(($target).hasClass('js-after-power-submit')){
            //提交按钮
            const frontHtmlId = $("#frontHtmlId").val();
            const htmlName = $("#htmlName").val();
            const htmlType = $("#htmlType").val();
            const htmlKeyword = $("#htmlKeyword").val();
            const htmlUrl = $("#htmlUrl").val();
            $.post("/front/updateFrontHtml",{
                frontHtmlId:frontHtmlId,
                htmlName:htmlName,
                htmlType:htmlType,
                htmlKeyword:htmlKeyword,
                htmlUrl:htmlUrl
            },function(data){
                if(data.success){
                    $(clickTr).parents("tr").children("td.table-html-name").html(htmlName);
                    $(clickTr).parents("tr").children("td.table-html-type").html(htmlType);
                    $(clickTr).parents("tr").children("td.table-html-keyword").html(htmlKeyword);
                    $(clickTr).parents("tr").children("td.table-html-url").html(htmlUrl);
                    modalClose();
                }else{
                    myAlert(data.error);
                }
            });
        }
    });

    /**关闭*/
    function modalClose(){
        $("#frontHtmlId").val('');
        $("#htmlName").val('');
        $("#htmlKeyword").val('');
        $("#htmlUrl").val('');
        $modal.modal('close');
    }

});