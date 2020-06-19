package team.kas.anime.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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

    void addcomment(@Param("pid") String pid ,@Param("uid") String uid, @Param("vid") String vid
            , @Param("content") String content,  @Param("date")Date date, @Param("owner") String owner,@Param("toname") String toname);


    String selectPName(String pid);


    //findByReplayId
}