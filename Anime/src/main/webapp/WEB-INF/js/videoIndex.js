layui.use(['layer'],function () {
    var layer=layui.layer;
    var $ = layui.jquery;

    var userid=$('#userid').text();
    var videoUserid=$('#videoUserid').text();
    var videoid=$('#videoid').text();
    var videolike=$('#videolike').text();
    window.sessionStorage.setItem("videoId",videoid);
    window.sessionStorage.setItem("userId",userid);
    $('fans0').click(function () {
        $.ajax({
            type:'post',
            url:'/Anime/userIndex/follow',
            dataType:'json',
            data:{'userid':userid,'videoUserid':videoUserid,'follow':1},
            success:function (res) {
                if(res.code==0)
                {
                    // window.location.href="/Anime/userIndex/fans";
                    window.location.reload("/Anime/userIndex/videoPlay")
                }
                else
                {
                    layer.msg("关注失败");
                }
            }
        })
    })
    $('fans1').click(function () {
        $.ajax({
            type:'post',
            url:'/Anime/userIndex/unfollow',
            dataType:'json',
            data:{'userid':userid,'videoUserid':videoUserid,'follow':0},
            success:function (res) {
                if(res.code==0)
                {
                    // window.location.href="/Anime/userIndex/fans";
                    window.location.reload("/Anime/userIndex/videoPlay")
                }
                else
                {
                    layer.msg("取关失败");
                }
            }
        })
    })
    $('#like').click(function () {
        $.ajax({
            type:'post',
            url:'/Anime/userIndex/like',
            dataType:'json',
            data:{'videoLike':videolike,'videoId':videoid,'like':1},
            success:function (res) {
                if (res.code==0)
                {
                    layer.msg("点赞成功");  ``
                }
                else
                {
                    layer.msg("点赞失败")
                }
            }
        })
    })
    $('#unlike').click(function () {
        $.ajax({
            type:'post',
            url:'/Anime/userIndex/unlike',
            dataType:'json',
            data:{'videoLike':videolike,'videoId':videoid,'like':0},
            success:function (res) {
                if (res.code==0)
                {
                    layer.msg("不喜欢成功");  ``
                }
                else
                {
                    layer.msg("不喜欢失败")
                }
            }
        })
    })
    $('#collection').click(function () {
        $.ajax({
            type:'post',
            url:'/Anime/userIndex/collection',
            dataType:'json',
            data:{'videoId':videoid,'userId':userid},
            success:function (res) {
                if (res.code==0)
                {
                    layer.msg("收藏成功");
                }
                else
                {
                    layer.msg("收藏失败")
                }
            }
        })
    })
})
//时间戳转换为日期格式
function formatDate(time) {
    var now = new Date(time);
    var year=now.getFullYear();  //取得4位数的年份
    var month=now.getMonth()+1;  //取得日期中的月份，其中0表示1月，11表示12月
    var date=now.getDate();      //返回日期月份中的天数（1到31）
    var hour=now.getHours();     //返回日期中的小时数（0到23）
    var minute=now.getMinutes(); //返回日期中的分钟数（0到59）
    var second=now.getSeconds(); //返回日期中的秒数（0到59）
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}

