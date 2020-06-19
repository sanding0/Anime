
var uid = window.sessionStorage.getItem("userId");
var vid = window.sessionStorage.getItem("videoId");

function digui(list){

	list.forEach(function (item , index) {
		var uid = item.owner;
		// var time = new Date(item.comment_time).toLocaleDateString().replace(/\//g, "-");
		var time = new Date(item.commentTime).toLocaleDateString().replace(/\//g, "-");
		var msg = item.content;
		var id = item.id;
		$(`<div class="list" style="background-color: white">
				<div class="userMsg">
				<div class="userHead"></div>
				<div class="userName">${uid}</div>
				<div class="msgTime">${time}</div>
				<button style="margin-left: 50px"  class="layui-btn layui-btn-radius layui-btn-xs  layui-btn-normal" onclick="reply(${id})" >回复</button>   							
				</div>
				<div class="detail">
				<div class="content">${msg}</div>				
				</div>
		</div>`).appendTo(".comment");
		if (item.childComment) {
			let $ul = $("<ul>").addClass("replayBox").appendTo($(".list").eq(index).find(".detail"));
			readData(item.childComment, $ul);
		}
	});
	function readData(list, target) {
		list.forEach(function (item, index) {
			var childId = item.id;
			$("<li>").css({"float":"left"}).html(item.owner + " 回复 " + item.toname + ":" + item.content + "&nbsp"+ "&nbsp"+ "&nbsp"+ "&nbsp").appendTo(target);
            $("<button>").addClass("layui-btn layui-btn-radius layui-btn-xs  layui-btn-normal").html("回复").appendTo(target)
				.click(function () {
					//获取到item的id
					reply(childId);
				})
			$("<li>").html("&nbsp").appendTo(target);
			$("<br>").appendTo(target);
			if (item.childComment) {
				console.log(item.childComment);
				readData(item.childComment, target);
			}
		})
	}
}



//添加对评论的评论（楼中楼）
function reply(id) {
	window.sessionStorage.setItem("id",id);
  	layui.use(['layer'],function () {
		layer.open({
			content: 'addreply.html',
			type: 2,
			title: "reply",
			area: ['40%', '25%'],
		});
  	})

}

//添加对评论的评论（楼中楼）
$("#replybtn").on("click", function () {
	layui.use(['layer'], function () {
		var content = $("#replycontent").val();
		var id = window.sessionStorage.getItem("id");
		var win = window.sessionStorage.getItem("win");
		$.ajax({
			type: 'post',
			url: '/Anime/comment/addreply',
			data: {
				'content': content,
				'pid': id,
				'uid': uid,
				'vid': vid,
			},
			success: function (res) {
				if (res.code == 0) {
					alert("评论成功！");
					layer.close(layer.index);
					parent.location.reload();
				} else {
					layer.msg(res.msg);
				}
				window.location.reload();
			}

		})
	})
});


//添加对视频的评论
$("#addcomment").on("click", function () {
	layui.use(['layer'], function(){
		var content = $("#content").val();
		$.ajax({
			type: "post",
			url: "/Anime/comment/addcomment",
			data: {
				'uid': uid,
				'vid': vid,
				'content': content,
			},
			success: function (res) {
				if (res.code == 0) {
					layer.msg(res.msg);
					window.location.reload();
				} else
					layer.msg(res.msg);
			}
		})
	})
})



