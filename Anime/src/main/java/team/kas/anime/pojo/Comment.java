package team.kas.anime.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    private Integer id;
    /**
     * 视频id
     */
    private String vid;
    /**
     * 被评论人
     */
    private String uid;
    /**
     * 时间
     */
    private Date commentTime;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 父评论id(可以为空)
     */
    private String pid;
    /**
     * 每一个评论里面都可能有自己的子评论集合
     */
    private String toname;
    private String owner;

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private List<Comment> childComment = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Comment> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<Comment> childComment) {
        this.childComment = childComment;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Comment(){
    }

    public Comment(Integer id, String vid, String uid, Date commentTime, String content, String pid, String toname, String owner) {
        this.id = id;
        this.vid = vid;
        this.uid = uid;
        this.commentTime = commentTime;
        this.content = content;
        this.pid = pid;
        this.toname = toname;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", vid='" + vid + '\'' +
                ", uid='" + uid + '\'' +
                ", commentTime=" + commentTime +
                ", content='" + content + '\'' +
                ", pid=" + pid +
                ", childComment=" + childComment +
                '}';
    }
}
