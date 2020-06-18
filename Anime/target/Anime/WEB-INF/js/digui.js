function digui(list){
	//var list = [{"id":1,"owner":"张凌迪","toname":"一条咸鱼","sendtime":"Dec 19, 2019 10:31:05 AM","content":"你这写的啥呀，还不如我回的多呢","articleId":1,"parentId":0,"childComment":[{"id":2,"owner":"胡自立","toname":"张凌迪","sendtime":"Dec 20, 2019 10:31:26 AM","content":"我觉得你就是个臭弟弟，我不服","articleId":1,"parentId":1,"childComment":[{"id":3,"owner":"黄周逍遥","toname":"胡自立","sendtime":"Dec 19, 2019 2:32:08 PM","content":"你也是个臭弟弟","articleId":1,"parentId":2,"childComment":[]},{"id":7,"owner":"lisi","toname":"胡自立","sendtime":"Dec 19, 2019 10:34:52 AM","content":"+1","articleId":1,"parentId":2,"childComment":[]}]}]},{"id":4,"owner":"高涵","toname":"一条咸鱼","sendtime":"Dec 19, 2019 10:32:45 AM","content":"有一说一，受益匪浅","articleId":1,"parentId":0,"childComment":[{"id":5,"owner":"zhangsan","toname":"高涵","sendtime":"Dec 19, 2019 10:33:13 AM","content":"纯属路人","articleId":1,"parentId":4,"childComment":[]},{"id":6,"owner":"lisi","toname":"高涵","sendtime":"Dec 19, 2019 10:33:54 AM","content":"我来打打酱油","articleId":1,"parentId":4,"childComment":[]}]},{"id":8,"owner":"wangwu","toname":"一条咸鱼","sendtime":"Dec 19, 2019 10:35:18 AM","content":"已经掌握了jdbc，奥里给","articleId":1,"parentId":0,"childComment":[]}]
	list.forEach(function (item , index) {
		var uid = item.owner;
		var time = new Date(item.comment_time).toLocaleDateString().replace(/\//g, "-");
		var msg = item.content;
		$(`<div class="list">
				<div class="userMsg">
				<div class="userHead"></div>
				<div class="userName">${uid}</div>
				<div class="msgTime">${time}</div>
				<button style="margin-left: 50px"  class="layui-btn layui-btn-radius layui-btn-xs  layui-btn-normal" onclick='reply()' id="reply"  >回复</button>   
				</div>
				<div class="detail">
				<div class="content">${msg}</div>
				
				</div>
		</div>`).appendTo(".comment");
		if (item.childComment) {
			let $ul = $("<ul>").addClass("replayBox").appendTo($(".list").eq(index).find(".detail"));
			readData(item.childComment, $ul);
		}
	})
	function readData(list, target) {
		list.forEach(function (item, index) {
			$("<li>").html(item.owner + " 回复 " + item.toname + ":" + item.content).appendTo(target);
			if (item.childComment) {
				console.log(item.childComment);
				readData(item.childComment, target);
			}
		})
	}
	
}

var uid = window.sessionStorage.setItem(userid);
var vid = window.sessionStorage.getItem(videoId);
var content = $("#content").text();
function addcomment() {

	$.ajax({
		type:"post",
		url:"/Anime/comment/addcomment",
		data:{
			'uid':uid,
			'vid':vid,
			'content':content,
		}

	})
}