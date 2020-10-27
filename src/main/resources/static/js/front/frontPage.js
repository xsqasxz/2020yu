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
        const takeEffect = $(this).parents("tr").children("td.table-html-take-effect").html();
        $("#frontHtmlId").val(frontHtmlId);
        $("#htmlName").val(htmlName);
        $("#htmlKeyword").val(htmlKeyword);
        $("#htmlUrl").val(htmlUrl);
        if(takeEffect=='是'){
            $("input[type=radio][name=takeEffect][value=true]").uCheck('check');
            $("input[type=radio][name=takeEffect][value=false]").uCheck('uncheck');
        }else{
            $("input[type=radio][name=takeEffect][value=true]").uCheck('uncheck');
            $("input[type=radio][name=takeEffect][value=false]").uCheck('check');
        }
        switch (htmlType.trim()){
            case '<span>模版</span>':
                getSelected($("#htmlType"),0);
                break;
            case '<span>首页模版</span>':
                getSelected($("#htmlType"),1);
                break;
            case '<span>详情模版</span>':
                getSelected($("#htmlType"),2);
                break;
        }
        $modal.modal();
    });

    /**点击修改后的弹出框*/
    $modal.find('.am-btn').on('click', function(e) {
        const $target = $(e.target);
        if (($target).hasClass('js-modal-close')) {
            //关闭添加窗口
            modalClose();
        }
        else if(($target).hasClass('js-after-power-submit')){
            //提交按钮
            const frontHtmlId = $("#frontHtmlId").val();
            const htmlName = $("#htmlName").val();
            const htmlType = $("#htmlType").val();
            const htmlKeyword = $("#htmlKeyword").val();
            const htmlUrl = $("#htmlUrl").val();
            const takeEffect = $("input[type=radio][name=takeEffect]:checked").val();
            $.post("/front/updateFrontHtml",{
                frontHtmlId:frontHtmlId,
                htmlName:htmlName,
                htmlType:htmlType,
                takeEffect:takeEffect,
                htmlKeyword:htmlKeyword,
                htmlUrl:htmlUrl
            },function(data){
                if(data.success){
                    $(clickTr).parents("tr").children("td.table-html-name").html(htmlName);

                    switch (htmlType){
                        case '0':
                            $(clickTr).parents("tr").children("td.table-html-type").html('<span>模版</span>');
                            break;
                        case '1':
                            $(clickTr).parents("tr").children("td.table-html-type").html('<span>首页模版</span>');
                            break;
                        case '2':
                            $(clickTr).parents("tr").children("td.table-html-type").html('<span>详情模版</span>');
                            break;
                    }

                    $(clickTr).parents("tr").children("td.table-html-keyword").html(htmlKeyword);
                    $(clickTr).parents("tr").children("td.table-html-url").html(htmlUrl);
                    $(clickTr).parents("tr").children("td.table-html-take-effect").html(takeEffect===true||takeEffect==='true'?'是':'否');
                    modalClose();
                }else{
                    myAlert(data.error);
                }
            });
        }
    });

    $('.js-front-page-preview').click(function(){
        let htmlType = $(this).parents("tr").children("td.table-html-type").html();
        htmlType = htmlType.trim();
        if (htmlType === '<span>模版</span>'){
          myAlert("只有首页模版和详情模版可以预览！");
        }else{
            window.open("http://localhost:8081/front/preview", "_blank",'height=1300,width=700,status=yes,toolbar=no,menubar=no,location=no')
        }
    })
    /**关闭*/
    function modalClose(){
        $("#frontHtmlId").val('');
        $("#htmlName").val('');
        $("#htmlKeyword").val('');
        $("#htmlUrl").val('');
        $("input[type=radio][name=takeEffect][value=true]").uCheck('uncheck');
        $("input[type=radio][name=takeEffect][value=false]").uCheck('uncheck');
        $modal.modal('close');
    }

});