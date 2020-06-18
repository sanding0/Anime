layui.use(['form', 'layer', 'jquery','carousel'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        carousel = layui.carousel,
         $ = layui.jquery;
        form.render();

    form.on('submit(login)',function(date){
        $.ajax({
            type:'post',
            url:'/Anime/home/login',
            dataType:'json',
            data:date.field,
            success:function (res) {
                if(res.code == 0){
                    $.ajax({
                        type: 'post',
                        url:'/Anime/userIndex/onload',
                        dataType: 'json',
                        success:function (res) {
                            if (res.code==0)
                            {
                                window.location.href = "/Anime/home/main";
                            }
                            else {
                                layer.msg("访问失败");
                            }
                        }
                    })
                }else {
                    layer.msg(res.msg,{icon:2})
                    changeCode()
                }
            }
        })
        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})

//点击以后切换验证码的函数
function changeCode() {
    //用一个变量接受标签img
    var img = document.getElementById("codeImg")
    // + new Date（）防止浏览器阻止相同的请求，加载缓存的数据   加“/”指根目录访问
    img.src = "/Anime/home/getCode?tt=" + new Date().getTime()
}