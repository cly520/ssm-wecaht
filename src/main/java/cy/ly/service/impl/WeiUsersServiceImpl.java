package cy.ly.service.impl;

import cy.ly.bean.WeiUsers;
import cy.ly.bean.WeiUsersExample;
import cy.ly.mappers.WeiUsersMapper;
import cy.ly.service.WeiUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeiUsersServiceImpl implements WeiUsersService {
    @Autowired
    private WeiUsersMapper weiUsersMapper;
    public int insert(WeiUsers weiUsers) {
        return weiUsersMapper.insertSelective(weiUsers);
    }

    public WeiUsers selectWeiUsersById(Integer id) {
        WeiUsers weiUsers = weiUsersMapper.selectByPrimaryKey(id);
        return weiUsers;
    }

    public WeiUsers selectWeiUsersByOpenId(String openid) {
        WeiUsersExample weiUsersExample = new WeiUsersExample();
        WeiUsersExample.Criteria criteria = weiUsersExample.createCriteria();
        criteria.andOpenidEqualTo(openid);
        List<WeiUsers> weiUsers = weiUsersMapper.selectByExample(weiUsersExample);
        if(weiUsers.size() != 0){
            for (WeiUsers weiUser : weiUsers) {
                return weiUser;
            }
        }
        return null;
    }
}
