layui.use(['table','form','upload','laydate'], function() {
    var table = layui.table
        , form = layui.form
        , upload = layui.upload
        , laydate = layui.laydate;
    var $ = layui.jquery;
    //执行一个 table 实例
    var uid=window.sessionStorage.getItem("uid");
    table.render({
        elem: '#video',
        height: 580,
        url: '/Anime/userInfo/getAllVideos?userid='+uid, //数据接口
        title: '视频表',
        page: true ,//开启分页
        toolbar: '#barbtn', //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        limits:[10,20,30,40],
        cols:
            [[ //表头
                { type: 'checkbox',fixed: 'left'},
                { field: 'id',title: 'ID',width: 80,sort: true,fixed: 'left'},
                { field: 'videoDesc',title: '标题', edit: 'text'},
                { field: 'videoPath',title: '播放',
                    templet: function(d){
                        var path=d.videoPath;
                        var status=d.status;
                        return '<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-danger" value="{{d.id}}" onclick="video(\''+path+','+status+'\')">点击播放</button>';
                    }},
                { field: 'likeCounts',title: '点赞次数',sort: true},
                { field: 'status',title: '状态', templet: '#switchTpl'},
                { field: 'comments',title: '收藏数',sort: true},
                { field: 'createTime',title: '创建时间',sort: true,
                    templet:function (d) {
                        var time=d.createTime;
                        return formatDate(time);
                    }},
                { field: 'remark',title: '视频描述', edit: 'text'},
                { fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
    });
    //监听行工具事件
    table.on('tool(videotest)',function (obj) {
        var data=obj.data;
        var layEvent=obj.event;
        if(layEvent=="delete")
        {
            layer.confirm('真的删除行么',function (index) {
                $.ajax({
                    type:'get',
                    url:'/Anime/userInfo/del?id='+data.id,
                    dataType:'json',
                    success:function (res) {
                        if (res.code==0)
                        {
                            obj.del();
                            layer.msg("删除成功")
                        }
                        else {
                            layer.msg("删除失败");
                        }
                    }
                })
                layer.close(index);
            })
        }
    });
    //监听头工具栏事件
    table.on('toolbar(videotest)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)//复选框数据
            ,data = checkStatus.data; //获取选中的数据 数组
        var arry=new Array();
        for(var i in data)
        {
            arry[i]=data[i].id;//取出id到数组
        }
        switch(obj.event){
            case 'add':
                layer.open({
                    type:2,
                    title:'视频上传',
                    area: ['50%','75%'],
                    content:'userAddVideo.html'
                });
                break;
            case 'update':
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请至少选择一行');
                }
                else
                {
                    //提示框
                    //用户信息删除
                    layer.confirm("确定删除吗？",function () {
                        $.ajax({
                            type:'post',
                            url:'/Anime/userInfo/delete',
                            dataType: 'json',
                            data:{
                                'ids':arry
                            },
                            traditional:true,//声明上传数据为数组
                            success:function (res) {
                                if(res.code==0)
                                {
                                    layer.msg("删除成功");
                                    $(".layui-laypage-btn").click();//刷新表格
                                    // table.reload("video",{})
                                }
                                else
                                {
                                    layer.msg("删除失败");
                                }
                            }
                        })
                        layer.close(index);
                    })
                }
                break;
        };
    });
//实现视频的上传
    upload.render({
        elem: '#upVideo',
        url: '/Anime/userInfo/upVideo', //改成您自己的上传接口
        accept: 'video', //视频
        done: function(res){
            if(res.code==0)
            {
                $('#videoPath').val(res.msg);
            }
            else
            {
                layer.msg(res.msg);
            }

        }
    });
    //普通图片上传
    var uploadInst = upload.render({
        elem: '#upImg'
        ,url: '/Anime/userInfo/upImg' //改成您自己的上传接口
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code==0){
                $('#coverPath').val(res.msg);
            }
            else
            {
                layer.msg(res.msg);
            }
            //上传成功
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #ff5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
    //表单提交
    form.on('submit(submit)', function(data){
        // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        $.ajax({
            type:'post',
            url:'/Anime/userInfo/add',
            dataType:'json',
            data:data.field,
            success:function (res) {
                if(res.code==0)
                {
                    layer.msg(res.msg);
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    table.reload('video',{})//表格重载
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});
//视频播放
function  video(path) {
    var status=path.charAt(path.length-1);//截取字符串，从i开始
    var path0=path.slice(0,path.length-2);//从i到j
    if(status==1)
    {
        layer.msg("该视频已被封禁，无法播放")
    }
    else
    {
        var videoPath="<video src=\"../"+path0+"\" width='100%' height='100%' controls='controls'></video>";
        layui.use(['layer'],function () {
            var layer=layui.layer;
            layer.open({
                type:1,
                title:"视频预览",
                area:['70%','80%'],
                content:videoPath
            })
        })
    }
}
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