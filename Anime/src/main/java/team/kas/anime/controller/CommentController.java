package team.kas.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import team.kas.anime.pojo.Comment;
import team.kas.anime.service.CommentService;

@RequestMapping("/comment")
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    @RequestMapping("/post")
    public String post(Comment comment) {

        if (comment.getParentComment().getId() != null) {
            comment.setPid(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        return " ";
    }


}
