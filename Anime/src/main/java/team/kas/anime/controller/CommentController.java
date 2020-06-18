package team.kas.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kas.anime.pojo.Comment;
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


@RequestMapping("/post")
    public String post(){
        return "comment";
}

}
