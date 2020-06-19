package team.kas.anime.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kas.anime.pojo.Comment;
import team.kas.anime.pojo.Result;
import team.kas.anime.service.CommentService;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/listComment")
    @ResponseBody
    public List<Comment> listComment(String vid){
      List<Comment> list = commentService.getCommentListByVideoId(vid);
      return list;
    }

    @RequestMapping("/addcomment")
    @ResponseBody
    public Result addcomment(String vid ,String uid ,String content){
        Result result = commentService.addcomment(vid,uid,content);
        return result;
    }

    @RequestMapping("/addreply")
    @ResponseBody
    public Result addreply(String content,Integer pid, String uid , String vid){
        Result result = commentService.addreply(content,pid,uid,vid);
        return result;
    }

    @RequestMapping("/post")
    public String post(){
        return "comment";
}

}
