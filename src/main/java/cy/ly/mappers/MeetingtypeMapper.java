package cy.ly.mappers;


import cy.ly.bean.Meetingtype;
import cy.ly.bean.MeetingtypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MeetingtypeMapper {
    int countByExample(MeetingtypeExample example);

    int deleteByExample(MeetingtypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Meetingtype record);

    int insertSelective(Meetingtype record);

    List<Meetingtype> selectByExampleWithRowbounds(MeetingtypeExample example, RowBounds rowBounds);

    List<Meetingtype> selectByExample(MeetingtypeExample example);

    Meetingtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Meetingtype record, @Param("example") MeetingtypeExample example);

    int updateByExample(@Param("record") Meetingtype record, @Param("example") MeetingtypeExample example);

    int updateByPrimaryKeySelective(Meetingtype record);

    int updateByPrimaryKey(Meetingtype record);
}