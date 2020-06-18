package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    Comment selectByPrimaryKey(String id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

//    int savecomment(Comment record);
//
//    List<Comment> findByParentIdNull(String pId);
//
//    List<Comment> findByParentIdNotNull(String id);
//
//    List<Comment> findByReplayId(String childId);

    List<Comment> getCommentListByVideoId(String vid);

    //findByReplayId
}