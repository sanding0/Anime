layui.use(['layer','jquery'],function (e) {
    var layer=layui.layer;
    var $ = layui.jquery;
    var userId=$('#userid').text();
    $('.img-link').click(function () {
        var id0=this.id;
        var id1=id0.substring(id0.length-1);
        var id=parseInt(id1)+1;//将string转为int
        var nickname=$('#userid').text();
        $.ajax({
            type: 'post',
            url: '/Anime/userIndex/video',
            dataType: 'json',
            data: {'id':id,'nickname':nickname},
            success: function (res) {
                if (res.code == 0) {
                    $.ajax({
                        type:'post',
                        url: '/Anime/userIndex/likeVideo',
                        dataType: 'json',
                        success:function (res) {
                            if (res.code==0)
                            {
                                $.ajax({
                                    type:'post',
                                    url:'/Anime/userIndex/fans',
                                    dataType:'json',
                                    data: {'id':id,'nickname':nickname},
                                    success:function (res) {
                                        if(res.code==0)
                                        {
                                            window.location.href = "/Anime/userIndex/videoPlay";
                                        }
                                        else
                                        {
                                            layer.msg("加载关注失败")
                                        }
                                    }
                                })
                            }
                            else
                            {
                                layer.msg("加载推荐视频失败");
                            }
                        }
                    })
                }
                else
                {
                    layer.msg("加载视频信息失败")
                }
            }
        })
    })
    $('#userid').click(function () {
        window.location.href="/Anime/userInfo/index";
    })
})
window.onload=function(){
    // var videoId=[[${session.video0}]];
    // alert(videoId);
}

