const $url_name="http://localhost:8081";
/**初始化富文本编辑框*/
tinymce.init({
    selector: 'textarea',
    plugins: 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
    toolbar: 'a11ycheck addcomment showcomments casechange checklist code formatpainter pageembed permanentpen table',
    toolbar_mode: 'floating',
    tinycomments_mode: 'embedded',
    tinycomments_author: 'Author name',
    language : "zh_CN"
});

$(function() {
    // const $home_html="";
    // let $html_type=1;
    // /**首页模版*/
    // $(".js-home-fr").click(function(){
    //     $html_type=1;
    //     //远程获取后返回
    //     tinymce.activeEditor.setContent($home_html);
    // });
    // /**详情模版*/
    // $(".js-details-fr").click(function(){
    //     $html_type=2;
    // });

    /**保存编辑后的模版*/
    $(".js-save-fr").click(function(){
        const $frontReleaseId = $("#frontReleaseId").val();
        const $html_type = $("#htmlType").val();
        const $html_name = $("#htmlName").val();
        const $take_effect = $('input[name="takeEffect"]:checked').val();
        const $html_keyword = $("#htmlKeyword").val();
        //得到编辑后的html
        const $html_text = tinymce.activeEditor.getContent();
        $.post("/front/saveFrontRelease",{
            htmlText:$html_text,
            frontReleaseId:$frontReleaseId,
            htmlType:$html_type,
            htmlName:$html_name,
            takeEffect:$take_effect,
            htmlKeyword:$html_keyword
        },function(data){
            if(data.success){
                myAlert(data.ok);
            }else{
                myAlert(data.error);
            }
        });
    });
});