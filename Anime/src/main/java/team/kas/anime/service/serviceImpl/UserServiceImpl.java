package team.kas.anime.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.kas.anime.dao.UserMapper;
import team.kas.anime.dao.VideoMapper;
import team.kas.anime.pojo.Result;
import team.kas.anime.pojo.User;
import team.kas.anime.pojo.Video;
import team.kas.anime.service.UserService;
import team.kas.anime.util.Md5Util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     *登录验证方法
     */
    @Override
    public Result login(String username, String password, String code, HttpServletRequest request) {
        Result result = new Result();
        String newCode = (String)request.getSession().getAttribute("code");
        if(newCode.equalsIgnoreCase(code)){
            User user = userMapper.selectByUsername(username);
            String pwd = Md5Util.encryption(username,password);
            if(user != null && pwd.equals(user.getPassword())){
                request.getSession().setAttribute("user",user);
                request.getSession().setMaxInactiveInterval(24*60*60);
                return result;
            }else{
                result.setCode(500);
                result.setMsg("用户名或密码错误！");
                return result;
            }
        }else{
            result.setCode(500);
            result.setMsg("验证码错误！");
            return result;
        }
    }

    @Override
    public Result verifyUsername(String username) {

        Result result = new Result();
        User user = userMapper.selectByUsername(username);
        if(user != null){
            result.setCode(500);
            result.setMsg("用户名重复");
            return result;
        }else{
            return result;
        }
    }
}
