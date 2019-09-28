package cy.ly.mappers;


import cy.ly.bean.Meetingpub;
import cy.ly.bean.MeetingpubExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MeetingpubMapper {
    int countByExample(MeetingpubExample example);

    int deleteByExample(MeetingpubExample example);

    int deleteByPrimaryKey(String id);

    int insert(Meetingpub record);

    int insertSelective(Meetingpub record);

    List<Meetingpub> selectByExampleWithRowbounds(MeetingpubExample example, RowBounds rowBounds);

    List<Meetingpub> selectByExample(MeetingpubExample example);

    Meetingpub selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Meetingpub record, @Param("example") MeetingpubExample example);

    int updateByExample(@Param("record") Meetingpub record, @Param("example") MeetingpubExample example);

    int updateByPrimaryKeySelective(Meetingpub record);

    int updateByPrimaryKey(Meetingpub record);
}