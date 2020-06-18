var i =0;

function  addtext(){

    var div = document.getElementById("div");
    div.innerHTML += "<div class='layui-form-item' >" +
        "<div id="+i+" class='layui-input-block' > <textarea  disabled='disabled' class='layui-textarea'  >" +
        "</textarea> <input type='button' onclick='deleteText(id)' id="+i+" value='删除'>  </div>   </div>";
    i++;

}

function deleteText(a){
       var d = document.getElementById(""+a+"");
       d.remove();
  }


layui.use('laypage', function(){
    var laypage = layui.laypage;

    //执行一个laypage实例
    laypage.render({
        elem: 'div' //注意，这里的 test1 是 ID，不用加 # 号
        ,count: 50 //数据总数，从服务端得到
    });
});

