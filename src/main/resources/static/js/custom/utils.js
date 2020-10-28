
/**公用的警告框*/
function myAlert(textVal){
    $('#warnAlert').empty();
    $('#warnAlert').append(textVal);
    $('#my-alert').modal();
}

/**公用的警告框*/
function myConfirm(textVal,determine,cancel){
    $('#warnPrompt').empty();
    $('#warnPrompt').append(textVal);
    $('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function() {
            if(determine){
                determine();
            }
        },
        onCancel: function() {
            if(cancel){
                cancel();
            }
        }
    });
}

/**输入框*/
function myPrompt(textVal,determine,cancel){
    $('#warnPrompt').empty();
    $('.am-modal-prompt-input').val('');
    $('#warnPrompt').append(textVal);
    $('#my-prompt').modal({
        relatedTarget: this,
        onConfirm: function() {
            if(determine){
                determine($('.am-modal-prompt-input').val());
            }
        },
        onCancel: function() {
            if(cancel){
                cancel($('.am-modal-prompt-input').val());
            }
        }
    });
}

/**在js中进行post跳转*/
function postSubmit(url,parameter){
    var $utilsFormId=$("#utilsFormId");
    $utilsFormId.attr('action', url);
    if(parameter){
        $.each(parameter,function(name,value) {
            $utilsFormId.append($('<input name="'+name+'" type="hidden" value="'+value+'"/>'));// 将input标签放入新创建的form中
        });
    }
    $utilsFormId.submit();
}

/**切换账号*/
function switch_account_post($afterAbilitieId,$userName) {
    //修改默认帐号，然后进行登录
    $.post("/after/updateAfterUserAbilityId",{
        afterAbilitieId:$afterAbilitieId
    },function(data){
        if(data.success){
            const switch_account_form_id=document.getElementById("switch_account_form_id");
            $("#username").val($userName);
            switch_account_form_id.submit();
        }else{
            myAlert(data.error);
        }
    });
}

/**分页*/
function pageFunction(pageNum){
    $('#pageNum').val(pageNum);
    obtainForm($('#pageNum')).submit();
}

/**找到父级的form控件*/
function obtainForm(element){
    if(element.parent().is("form")){
        return element.parent();
    }else{
        obtainForm(element.parent());
    }
}

/**给selected初始化值*/
function getSelected($selected,$val){
    var $o = $selected.find('option[value="'+$val+'"]');
    if($o.get(0)){
        $o.attr('selected', !$o.get(0).selected);
    }
}