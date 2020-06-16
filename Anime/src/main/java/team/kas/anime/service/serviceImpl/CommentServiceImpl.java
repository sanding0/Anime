package team.kas.anime.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kas.anime.dao.CommentMapper;
import team.kas.anime.dao.UserMapper;
import team.kas.anime.pojo.Comment;
import team.kas.anime.pojo.User;
import team.kas.anime.service.CommentService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listComment() {
        //用-1来查找所有父级评论
        List<Comment> comments = commentMapper.findByParentIdNull("-1");

        //利用迭代来查找所有一级评论
        for(Comment comment : comments){
            String id = comment.getId();
            String uId = comment.getUid();

            //找到一级评论存进listComment里
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);

            String parentNickname1  = userMapper.selectNickNameById(uId);

            //查询出子评论
            combineChildren(childComments , parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }


    /**
     * @Description: 查询出子评论
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments , String parentNickname1) {
        //判断是否有一级子回复
        if (childComments.size() > 0) {
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String uid = childComment.getUid();
                String id = childComment.getId();
                String parentNickname = userMapper.selectNickNameById(uid);
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);

                //查询二级以及所有子集回复
                recursively(id, parentNickname);
            }
        }
    }


    /**
     * @Description: 循环迭代找出子集回复
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
    private void recursively (String childId , String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByReplayId(childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String uid = replayComment.getUid();
                String parentNickname =  userMapper.selectNickNameById(uid);
                replayComment.setParentNickname(parentNickname1);
                String replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }



    @Override
    public int saveComment(Comment comment) {
        comment.setCommentTime(new Date());
       return commentMapper.savecomment(comment);
    }
}
