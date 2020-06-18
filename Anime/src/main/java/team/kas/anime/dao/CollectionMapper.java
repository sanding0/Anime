package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.Collections;

public interface CollectionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Collections record);

    Collections selectByPrimaryKey(String id);

    List<Collections> selectAll();

    int updateByPrimaryKey(Collections record);
}