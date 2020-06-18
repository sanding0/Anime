package team.kas.anime.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import team.kas.anime.pojo.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    Video selectByPrimaryKey(String id);

    List<Video> selectAll(@Param("page") Integer page, @Param("limit") Integer limit, @Param("userid") String userid);

    int updateByPrimaryKey(Video record);

    List<Video> selectByCountsUp(@Param("string") String string);

    Video selectByNum(@Param("num") Integer num, @Param("number") Integer number);

    Video selectByNumber(@Param("i") Integer i, @Param("j") Integer j);

    int updateByLike(@Param("num") Integer num, @Param("id") String id);

    Integer getCount();
}