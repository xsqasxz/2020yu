$(function() {
    const $modal = $('#my-update-front-page');
    const $modal_top = $('#my-top-fp');
    let clickTr;
    $(".js-front-page-update").click(function(){
        clickTr = this;
        let frontHtmlId = $(this).val();
        let htmlName = $(this).parents("tr").children("td.table-html-name").html();
        let htmlType = $(this).parents("tr").children("td.table-html-type").html();
        let htmlKeyword = $(this).parents("tr").children("td.table-html-keyword").html();
        let takeEffect = $(this).parents("tr").children("td.table-html-take-effect").html();
        $("#frontHtmlId").val(frontHtmlId);
        $("#htmlName").val(htmlName);
        $("#htmlKeyword").val(htmlKeyword);
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
        let $target = $(e.target);
        if (($target).hasClass('js-modal-close')) {
            //关闭添加窗口
            modalClose();
        }
        else if(($target).hasClass('js-after-power-submit')){
            //提交按钮
            let frontHtmlId = $("#frontHtmlId").val();
            let htmlName = $("#htmlName").val();
            let htmlType = $("#htmlType").val();
            let htmlKeyword = $("#htmlKeyword").val();
            let takeEffect = $("input[type=radio][name=takeEffect]:checked").val();
            $.post("/front/updateFrontHtml",{
                frontHtmlId:frontHtmlId,
                htmlName:htmlName,
                htmlType:htmlType,
                takeEffect:takeEffect,
                htmlKeyword:htmlKeyword
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
                    $(clickTr).parents("tr").children("td.table-html-take-effect").html(takeEffect===true||takeEffect==='true'?'是':'否');
                    modalClose();
                }else{
                    myAlert(data.error);
                }
            });
        }
        else if(($target).hasClass('js-home-fp')) {
            console.log(123456)
        }
    });
    /**顶部点击*/
    $modal_top.find('.am-btn').on('click', function(e) {
        let $target = $(e.target);
        if(($target).hasClass('js-home-fp')) {
            myConfirm("确认是否生产页面",function(){
                $.post("/front/crdateHomeHtml",function(data){
                    if(data.success){
                        myAlert(data.ok);
                    }else{
                        myAlert(data.error);
                    }
                });
            });
        }
        else if(($target).hasClass('js-details-fp')) {
            myConfirm("确认是否生产页面",function(){
                $.post("/front/crdateDetailedHtml",function(data){
                    if(data.success){
                        myAlert(data.ok);
                    }else{
                        myAlert(data.error);
                    }
                });
            });
        }
        else if(($target).hasClass('js-teahouse-fp')) {
            myConfirm("确认是否生产页面",function(){
                $.post("/front/crdatedetailsHtml",function(data){
                    if(data.success){
                        myAlert(data.ok);
                    }else{
                        myAlert(data.error);
                    }
                });
            });
        }
    });


    $('.js-front-page-preview').click(function(){
        let frontHtmlId = $(this).val();
        let htmlType = $(this).parents("tr").children("td.table-html-type").html();
        htmlType = htmlType.trim();
        if (htmlType === '<span>模版</span>'){
          myAlert("只有首页模版和详情模版可以预览！");
        }else{
            window.open("/front/preview?frontReleaseId="+frontHtmlId, "_blank",'height=1300,width=700,status=yes,toolbar=no,menubar=no,location=no')
        }
    });

    /**
     * 删除模版
     */
    $('.js-front-page-delete').click(function(){
        let frontHtmlId = $(this).val();
        clickTr = this;
        myConfirm("确认是否删除",function(){
            $.post("/front/deleteFrontHtml",{
                frontReleaseId:frontHtmlId
            },function(data){
                if(data.success){
                    $(clickTr).parents("tr").remove();
                }else{
                    myAlert(data.error);
                }
            });
        });
    });

    /**关闭*/
    function modalClose(){
        $("#frontHtmlId").val('');
        $("#htmlName").val('');
        $("#htmlKeyword").val('');
        $("input[type=radio][name=takeEffect][value=true]").uCheck('uncheck');
        $("input[type=radio][name=takeEffect][value=false]").uCheck('uncheck');
        $modal.modal('close');
    }

});