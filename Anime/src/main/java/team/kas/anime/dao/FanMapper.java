package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.Fan;

public interface FanMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fan record);

    Fan selectByPrimaryKey(String id);

    List<Fan> selectAll();

    int updateByPrimaryKey(Fan record);
}