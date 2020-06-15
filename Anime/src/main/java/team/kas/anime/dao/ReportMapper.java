package team.kas.anime.dao;

import java.util.List;
import team.kas.anime.pojo.Report;

public interface ReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(Report record);

    Report selectByPrimaryKey(String id);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}