package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Collection record);

    Collection selectByPrimaryKey(String id);

    List<Collection> selectAll();

    int updateByPrimaryKey(Collection record);
}