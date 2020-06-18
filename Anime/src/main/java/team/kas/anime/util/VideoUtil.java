package team.kas.anime.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 视频解析工具类
 */
public class VideoUtil {
    public static MultimediaInfo video(HttpServletRequest request,String name)
    {
        //获取服务器中存放文件的目录
        String serverPath=request.getSession().getServletContext().getRealPath("/WEB-INF/video/");
        //拼接具体的文件路径
        String path=serverPath+name;
        //创建一个文件对象--需要解析的视频文件
        File file=new File(path);
        //专门用来解析视频
        Encoder encoder=new Encoder();
        //告诉encoder去解析谁
        try {
            //info就是将视频转换为java对象
            MultimediaInfo info=encoder.getInfo(file);
            return info;
        } catch (EncoderException e) {
            e.printStackTrace();
            return null;
        }
    }

}
