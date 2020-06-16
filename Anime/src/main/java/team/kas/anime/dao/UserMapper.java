package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    String selectNickNameById(String uId);
}