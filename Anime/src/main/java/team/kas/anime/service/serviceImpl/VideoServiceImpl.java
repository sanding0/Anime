package team.kas.anime.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kas.anime.dao.CollectionMapper;
import team.kas.anime.dao.FanMapper;
import team.kas.anime.dao.UserMapper;
import team.kas.anime.dao.VideoMapper;
import team.kas.anime.pojo.*;
import team.kas.anime.service.VideoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FanMapper fanMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Result onload(HttpServletRequest  request) {
        Result result=new Result();
        String string="time";
        List<Video> videoList=videoMapper.selectByCountsUp(string);
        /**
         * 填充videoList，防止当videoList[i]为空时session为空，页面加载不成功
         */
        Video video=getVideo();
        result.setMsg("视频加载完成");
        for (int i=videoList.size();i<8;i++)
        {
            videoList.add(i,video);
        }
        request.getSession().setAttribute("video0",videoList.get(0));
        request.getSession().setAttribute("video1",videoList.get(1));
        request.getSession().setAttribute("video2",videoList.get(2));
        request.getSession().setAttribute("video3",videoList.get(3));
        request.getSession().setAttribute("video4",videoList.get(4));
        request.getSession().setAttribute("video5",videoList.get(5));
        request.getSession().setAttribute("video6",videoList.get(6));
        request.getSession().setAttribute("video7",videoList.get(7));
        return result;
    }

    @Override
    public Result video(HttpServletRequest request, String id) {
        Result result=new Result();
        Integer i = Integer.parseInt(id);
        Integer j=i-1;
        Video video=videoMapper.selectByNum(j,i);
        Video video2=videoMapper.selectByNumber(j,i);
        if (video!=null)
        {
            User user=userMapper.selectByNickName(video.getUid());
            request.getSession().setAttribute("video",video);
            request.getSession().setAttribute("videoUser",user);
            //点击增加播放量
            try {
                Video video1=video2;
                video1.setUid(video2.getUid());
                video1.setPlayCounts(video.getPlayCounts()+1);
                videoMapper.updateByPrimaryKey(video1);
                return result;
            }
           catch (Exception e)
           {
               e.printStackTrace();
               result.setCode(500);
               result.setMsg("error");
               return result;
           }
        }
        else
        {
            result.setCode(500);
            result.setMsg("error");
            return result;
        }
    }

    @Override
    public Result likeVideo(HttpServletRequest request) {
        Result result=new Result();
        String string="like";
        List<Video> videoList=videoMapper.selectByCountsUp(string);
        Video video=getVideo();
        for (int i=videoList.size();i<4;i++)
        {
            videoList.add(i,video);
        }
        request.getSession().setAttribute("likeVideo0",videoList.get(0));
        request.getSession().setAttribute("likeVideo1",videoList.get(1));
        request.getSession().setAttribute("likeVideo2",videoList.get(2));
        request.getSession().setAttribute("likeVideo3",videoList.get(3));
        return result;
    }

    @Override
    public Result fans(HttpServletRequest request,String nickname,String id) {
        Result result=new Result();
        Integer i = Integer.parseInt(id);
        Integer j=i-1;
        Video video=videoMapper.selectByNumber(j,i);//通过视频排序，获取视频上传用户id
        User user=userMapper.selectByNickName(nickname);//通过当前用户昵称获取用户id
        try {
            Fan fan=fanMapper.selectByFanid(user.getId(),video.getUid());
            if (fan!=null)
            {
                result.setCount(1);
                request.getSession().setAttribute("fanOrFollow",1);
                return result;
            }
            else
            {
                result.setCount(0);
                request.getSession().setAttribute("fanOrFollow",0);
                return result;
            }
        }
       catch (Exception e)
       {
           e.printStackTrace();
           request.getSession().setAttribute("fanOrFollow",0);
           result.setCode(500);
           result.setMsg("加载失败");
           return result;
       }
    }

    @Override
    public Result follow(HttpServletRequest request, String userid, String videoUserid,Integer follow) {
        Result result=new Result();
        if(follow==1)
        {
            try {
                fanMapper.deleteBYId(userid,videoUserid);
                return result;
            }catch (Exception e)
            {
                result.setCode(500);
                result.setMsg("error");
                return result;
            }
        }
        else
        {
            Fan fan=new Fan();
            String id= UUID.randomUUID().toString();
            fan.setId(id);
            fan.setFanId(userid);
            fan.setUid(videoUserid);
            try {
                fanMapper.insert(fan);
                return result;
            }catch (Exception e)
            {
                result.setCode(500);
                result.setMsg("error");
                return result;
            }
        }
    }

    @Override
    public Result like(String videoLike,Integer like,String videoId) {
        Result result=new Result();
        Integer i=Integer.parseInt(videoLike);
        if (like==1)
        {
            try {
                videoMapper.updateByLike(i+1,videoId);
                return result;
            }
           catch (Exception e)
           {
               result.setCode(500);
               result.setMsg("error");
               return result;
           }
        }
       else
        {
            try {
                videoMapper.updateByLike(i-1,videoId);
                return result;
            }catch (Exception e)
            {
                result.setCode(500);
                result.setMsg("error");
                return result;
            }
        }
    }

    @Override
    public Result collection(String videoId, String userId) {
        Result result=new Result();
        try {
            Collections collections=new Collections();
            String id= UUID.randomUUID().toString();
            collections.setId(id);
            collections.setUid(userId);
            collections.setVid(videoId);
            collectionMapper.insert(collections);
            return result;
        }catch (Exception e)
        {
            result.setCode(500);
            result.setMsg("eroor");
            return result;
        }

    }
    //创建默认视频对象4
    private Video getVideo()
    {
        Video video=new Video();
        video.setId("error");
        video.setCoverPath("/img/indexImg/cont/main_pic.jpg");
        video.setVideoDesc("视频名称");
        video.setVideoSeconds(999.0f);
        video.setUid("用户");
        video.setPlayCounts(9999999l);
        return video;
    }
}
