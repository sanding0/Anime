package team.kas.anime.service;

import team.kas.anime.pojo.Result;

import javax.servlet.http.HttpServletRequest;

public interface VideoService {
    //加载主页面视频信息
    Result onload(HttpServletRequest request);
    //加载视频详情页要播放的视频信息
    Result video(HttpServletRequest request, String id);
    //加载推荐视频信息
    Result likeVideo(HttpServletRequest request);
    //加载当前用户关注信息
    Result fans(HttpServletRequest request, String nickname, String id);
    //用户关注与取关
    Result follow(HttpServletRequest request, String userid, String videoUserid, Integer follow);
    //用户点赞与踩
    Result like(String videoLike, Integer like, String videoId);
    //用户收藏视频
    Result collection(String videoId, String userId);
}
