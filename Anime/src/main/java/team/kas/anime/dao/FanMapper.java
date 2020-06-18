package team.kas.anime.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import team.kas.anime.pojo.Fan;

public interface FanMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fan record);

    Fan selectByPrimaryKey(String id);

    List<Fan> selectAll();

    int updateByPrimaryKey(Fan record);

    Fan selectByFanid(@Param("f") String id,@Param("u") String uid);

    Integer deleteBYId(@Param("userid") String userid,@Param("videoUserid") String videoUserid);
}