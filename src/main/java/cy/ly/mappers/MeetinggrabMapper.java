package cy.ly.mappers;


import cy.ly.bean.Meetinggrab;
import cy.ly.bean.MeetinggrabExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MeetinggrabMapper {
    int countByExample(MeetinggrabExample example);

    int deleteByExample(MeetinggrabExample example);

    int deleteByPrimaryKey(String id);

    int insert(Meetinggrab record);

    int insertSelective(Meetinggrab record);

    List<Meetinggrab> selectByExampleWithRowbounds(MeetinggrabExample example, RowBounds rowBounds);

    List<Meetinggrab> selectByExample(MeetinggrabExample example);

    Meetinggrab selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Meetinggrab record, @Param("example") MeetinggrabExample example);

    int updateByExample(@Param("record") Meetinggrab record, @Param("example") MeetinggrabExample example);

    int updateByPrimaryKeySelective(Meetinggrab record);

    int updateByPrimaryKey(Meetinggrab record);
}