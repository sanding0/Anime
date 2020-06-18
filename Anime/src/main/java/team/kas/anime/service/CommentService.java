package team.kas.anime.service;


import team.kas.anime.pojo.Comment;
import java.util.List;



public interface CommentService {
    //查询评论列表
    List<Comment> getCommentListByVideoId(String vid);

    //保存评论

}
