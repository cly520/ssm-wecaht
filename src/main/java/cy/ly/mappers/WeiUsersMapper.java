package cy.ly.mappers;


import cy.ly.bean.WeiUsers;
import cy.ly.bean.WeiUsersExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface WeiUsersMapper {
    int countByExample(WeiUsersExample example);

    int deleteByExample(WeiUsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeiUsers record);

    int insertSelective(WeiUsers record);

    List<WeiUsers> selectByExampleWithRowbounds(WeiUsersExample example, RowBounds rowBounds);

    List<WeiUsers> selectByExample(WeiUsersExample example);

    WeiUsers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeiUsers record, @Param("example") WeiUsersExample example);

    int updateByExample(@Param("record") WeiUsers record, @Param("example") WeiUsersExample example);

    int updateByPrimaryKeySelective(WeiUsers record);

    int updateByPrimaryKey(WeiUsers record);
}