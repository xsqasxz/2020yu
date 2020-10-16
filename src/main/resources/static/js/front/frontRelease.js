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
    const $home_html="<article class=\"blog-main\">\n" +
        "      <h3 class=\"am-article-title blog-title\">\n" +
        "        <a href=\""+$url_name+"/blog/detailed\">Spring Boot项目篇</a>\n" +
        "      </h3>\n" +
        "\n" +
        "      <h4 class=\"am-article-meta blog-meta\">最新修改时间：2014/06/17</h4>\n" +
        "\n" +
        "      <div class=\"am-g blog-content\">\n" +
        "        <div class=\"am-u-lg-7\">\n" +
        "          <p>对于Spring Boot学习记录篇，按照下面教程可以实现0基础学习。</p>\n" +
        "          <p>Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。\n" +
        "            该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。\n" +
        "            通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。</p>\n" +
        "        </div>\n" +
        "        <div class=\"am-u-lg-5\">\n" +
        "          <p><img src=\"../image/blog/spring-logo.gif\"></p>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "\n" +
        "      <div class=\"am-g\">\n" +
        "        <div class=\"am-u-sm-12\">\n" +
        "          <p>SpringBoot是由Pivotal团队在2013年开始研发、2014年4月发布第一个版本的全新开源的轻量级框架。\n" +
        "            它基于Spring4.0设计，不仅继承了Spring框架原有的优秀特性，而且还通过简化配置来进一步简化了Spring应用的整个搭建和开发过程。\n" +
        "            另外SpringBoot通过集成大量的框架使得依赖包的版本冲突，以及引用的不稳定性等问题得到了很好的解决。</p>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "    </article>";
    let $html_type=1;
    /**首页模版*/
    $(".js-home-fr").click(function(){
        $html_type=1;
        //远程获取后返回
        tinymce.activeEditor.setContent($home_html);
    });
    /**详情模版*/
    $(".js-details-fr").click(function(){
        $html_type=2;
    });

    /**保存编辑后的模版*/
    $(".js-save-fr").click(function(){
        //得到编辑后的html
        const $html_text = tinymce.activeEditor.getContent();
        $.post("/front/saveFrontRelease",{
            htmlText:$html_text,
            htmlType:$html_type
        },function(data){
            if(data.success){
                myAlert(data.ok);
            }else{
                myAlert(data.error);
            }
        });
    });
});