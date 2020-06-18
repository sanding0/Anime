package team.kas.anime.service;

import org.springframework.stereotype.Service;
import team.kas.anime.pojo.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Result login(String username, String password, String code, HttpServletRequest request);

    Result verifyUsername(String username);
}
