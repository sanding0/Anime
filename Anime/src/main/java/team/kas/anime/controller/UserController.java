package team.kas.anime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.kas.anime.pojo.Result;
import team.kas.anime.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("verifyUsername")
    @ResponseBody
    public Result verifyUsername(String username){
        return userService.verifyUsername(username);
    }
}
