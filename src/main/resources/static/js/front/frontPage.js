$(function() {
    const $modal = $('#my-update-front-page');
    let clickTr;
    $(".js-front-page-update").click(function(){
        clickTr = this;
        const frontHtmlId = $(this).val();
        const htmlName = $(this).parents("tr").children("td.table-html-name").html();
        const htmlType = $(this).parents("tr").children("td.table-html-type").html();
        const htmlKeyword = $(this).parents("tr").children("td.table-html-keyword").html();
        const htmlUrl = $(this).parents("tr").children("td.table-html-url").html();
        $("#frontHtmlId").val(frontHtmlId);
        $("#htmlName").val(htmlName);
        $("#htmlType").val(htmlType);
        $("#htmlKeyword").val(htmlKeyword);
        $("#htmlUrl").val(htmlUrl);
        $modal.modal();
    });

    $modal.find('.am-btn').on('click', function(e) {
        const $target = $(e.target);
        if (($target).hasClass('js-modal-close')) {
            //关闭添加窗口
            $("#afterPowerId").val('');
            $("#powerUrl").val('');
            $("#authority").val('');
            $("#powerAlias").val('');
            $modal.modal('close');
        }else if(($target).hasClass('js-after-power-submit')){
            const afterPowerId = $("#afterPowerId").val();
            const authority = $("#authority").val();
            const powerUrl = $("#powerUrl").val();
            const powerAlias = $("#powerAlias").val();
            $.post("/after/updateAfterPower",{
                afterPowerId:afterPowerId,
                authority:authority,
                powerAlias:powerAlias,
                powerUrl:powerUrl
            },function(data){
                if(data.success){
                    $(clickTr).parents("tr").children("td.table-authority").html(authority);
                    $(clickTr).parents("tr").children("td.table-powerAlias").html(powerAlias);
                    $(clickTr).parents("tr").children("td.table-powerUrl").html(powerUrl);
                    $("#afterPowerId").val('');
                    $("#powerUrl").val('');
                    $("#authority").val('');
                    $("#powerAlias").val('');
                    $modal.modal('close');
                }else{
                    myAlert(data.error);
                }
            });
        }
    });

});