package team.kas.anime.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kas.anime.pojo.Result;
import team.kas.anime.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response){

        ValidateCode validateCode =new ValidateCode(160,40,4,50);
        String code = validateCode.getCode();
        request.getSession().setAttribute("code",code);
        request.getSession().setMaxInactiveInterval(300);
        try {
            validateCode.write(response.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password, String code, HttpServletRequest request){
        return userService.login(username,password,code,request);
    }

    @RequestMapping("/main")
    public String main(){
        return "userIndex";
    }
}
