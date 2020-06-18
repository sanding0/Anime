package team.kas.anime.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kas.anime.dao.CommentMapper;
import team.kas.anime.pojo.Comment;
import team.kas.anime.service.CommentService;
import java.util.ArrayList;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentListByVideoId(String vid){
        //查询当前文章所有的评论
        List<Comment> commentListByVideoId = null;

        commentListByVideoId = commentMapper.getCommentListByVideoId(vid);

        //定义存储最终树状评论结果的集合
        List<Comment> commentList = new ArrayList<>();//长度是2
        //定义集合存储所有的父评论
        List<Comment> parents = new ArrayList<>();
        //遍历commentListByArticleId（直接数据库查询出来的评论集合）
        for(Comment comment:commentListByVideoId){
            //判断comment是不是根评论
            if(comment.getPid()==0){
                //根评论
                commentList.add(comment);
                parents.add(comment);
            }else{
                //遍历所有父评论，去找每个父评论的子评论
                for(Comment parent:parents){
                    if(parent.getId().equals(comment.getPid())){
                        //找到了自己的子评论
                        parent.getChildComment().add(comment);
                        //comment也有可能是别人的父评论，所以添加到父评论集合中
                        parents.add(comment);
                        break;
                    }
                }
            }
        }
        return commentList;
    }
}
