package team.kas.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kas.anime.pojo.Result;
import team.kas.anime.pojo.Video;
import team.kas.anime.service.VideoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userIndex")
public class UserIndexCtrl {
    @Autowired
    private VideoService videoService;

    @RequestMapping("loginout")
    private String loginout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "login";
    }
    @RequestMapping("/onload")
    @ResponseBody
    private Result onload(HttpServletRequest request)
    {
        Result result = videoService.onload(request);
        return result;
    }
    @RequestMapping("/video")
    @ResponseBody
    private Result video(HttpServletRequest request,String id,String nickname)
    {
        Result result=videoService.video(request,id);
        return result;
    }
    @RequestMapping("/likeVideo")
    @ResponseBody
    private Result likeVideo(HttpServletRequest request)
    {
        Result result=videoService.likeVideo(request);
        return result;
    }
    @RequestMapping("/fans")
    @ResponseBody
    private Result fans(HttpServletRequest request,String nickname,String id)
    {
        Result result= videoService.fans(request,nickname,id);
        return result;
    }
    @RequestMapping("/videoPlay")
    private String videoPlay(HttpServletRequest request)
    {
        return "videoIndex";
    }
    @RequestMapping("follow")
    @ResponseBody
    private Result follow(HttpServletRequest request,String userid,String videoUserid,Integer follow)
    {
        Result result=videoService.follow(request,userid,videoUserid,follow);
        return result;
    }
    @RequestMapping("/like")
    @ResponseBody
    private Result like(String videoLike,Integer like,String videoId)
    {
        Result result=videoService.like(videoLike,like,videoId);
        return result;
    }
    @RequestMapping("/collection")
    @ResponseBody
    private Result collection(String videoId,String userId)
    {
        Result result=videoService.collection(videoId,userId);
        return result;
    }
}
