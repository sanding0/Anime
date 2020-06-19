package team.kas.anime.service;


import team.kas.anime.pojo.Comment;
import team.kas.anime.pojo.Result;

import java.util.List;



public interface CommentService {
    //查询评论列表
    List<Comment> getCommentListByVideoId(String vid);

    Result addcomment(String vid, String uid, String content);

    Result addreply(String content, Integer pid, String uid, String vid);

    //保存评论

}
