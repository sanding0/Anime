package team.kas.anime.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kas.anime.dao.CommentMapper;
import team.kas.anime.dao.UserMapper;
import team.kas.anime.dao.VideoMapper;
import team.kas.anime.pojo.Comment;
import team.kas.anime.pojo.Result;
import team.kas.anime.pojo.User;
import team.kas.anime.pojo.Video;
import team.kas.anime.service.CommentService;

import java.beans.IntrospectionException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;

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
            if(comment.getPid().equals("0")){
                //根评论
                commentList.add(comment);
                parents.add(comment);
            }else{
                //遍历所有父评论，去找每个父评论的子评论
                for(Comment parent:parents){
                    if(parent.getId().toString().equals(comment.getPid())){
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

    @Override
    public Result addcomment(String vid, String uid, String content) {
        Result result = new Result();
        String id = UUID.randomUUID().toString();
        User user = userMapper.selectByPrimaryKey(uid);
        String owner = user.getNickname();
        Date date = new Date();
        String pid = "0";
        String toname = "";
        if(content==null || content ==""){
            result.setCode(300);
            result.setMsg("内容为空!请输入评论后提交!");
            return result;
        }else{
            try {
                commentMapper.addcomment(pid,uid,vid,content,date,owner,toname);
                result.setCode(0);
                result.setMsg("评论成功!");
            }catch (Exception e){
                e.printStackTrace();
                result.setCode(500);
                result.setMsg("网络繁忙，请稍后再试！");
            }
        }
       return  result;
    }

    @Override
    public Result addreply(String ReplyContent, Integer parentId, String userid, String videoId) {
        Result result = new Result();
        String pid = parentId.toString();
        String uid = userid;
        String vid = videoId;
        String content = ReplyContent;
        Date date = new Date();
        User user = userMapper.selectByPrimaryKey(userid);
        String owner = user.getNickname();
        String toname = commentMapper.selectPName(pid);
        if(content==null || content.equals("")){
            result.setCode(300);
            result.setMsg("内容为空!请输入评论后提交!");
            return result;
        }else{
            try {
                commentMapper.addcomment(pid,uid,vid,content,date,owner,toname);
                result.setCode(0);
                result.setMsg("评论成功!");
            }catch (Exception e){
                e.printStackTrace();
                result.setCode(500);
                result.setMsg("网络繁忙，请稍后再试！");
            }
        }
        return result;
    }
}
